import React, { ChangeEvent, useEffect, useRef, useState } from 'react'
import './style.css'
import FavoriteItem from 'components/FavoriteItem/index';
import { Board, CommentListItem, FavoriteListItem } from 'types/enum/interface';
import { commentListMock, favoriteListMock } from 'mocks';
import CommentItem from 'components/CommentItem';
import Pagination from 'components/Pagination';

import defaultProfileImage from 'assets/image/default-profile-image.png'
import { useNavigate, useParams } from 'react-router-dom';
import { BOARD_PATH, BOARD_UPDATE_PATH, MAIN_PATH, USER_PATH } from 'constant';

import { useLoginUserStore } from 'stores';
import BoardUpdate from '../Update';
import { DeleteBoardRequest, IncreaseViewCountRequest, PostCommentRequest, PutFavoriteRequest, getBoardRequest, getCommentListRequest, getFavoriteListRequest } from 'apis';
import GetBoardResponseDto from 'apis/response/board/get-board.response.dto';
import { ResponseDto } from 'apis/response';
import { DeleteBoardResponseDto, GetCommentListResponseDto, GetFavoriteResponseDto, IncreaseViewCountResponseDto, PostCommentResponseDto, PutFavoriteResponseDto } from 'apis/response/board';

import dayjs from 'dayjs';
import { useCookies } from 'react-cookie';
import { PostCommentRequestDto } from 'apis/request/board';

import { usePagination } from 'hooks';



// component: 게시물 상세 화면 컴포넌트 //
export default function BoardDetail() {

  
  //  state:  게시물 번호 path variable 상태  //
  const { boardNumber } = useParams();
  //  state:  로그인 유저 상태  //
  const { loginUser } = useLoginUserStore();
  //  state:  쿠키 상태  //
  const [cookies, setCookies] = useCookies();

  //  function:  네비게이트 함수  //
  const navigator = useNavigate();
  //  function:  IncreaseViewCountRequest 처리 함수  //
  const increaseViewCountResponse = (responseBody: IncreaseViewCountResponseDto | ResponseDto | null) => {
    if(!responseBody) return;
    const { code } = responseBody;
    if(code === 'NB') alert('존재하지 않는 게시물입니다.');
    if(code === 'DBE') alert('데이터베이스 오류입니다.');
  }

  // component:  게시물 상세 화면 상단 컴포넌트
  const BoardDetailTop = () => {

    //state:  작성자 여부 상태  //
    const [isWriter, setIsWriter] = useState<boolean>(false);
    //  state:  게시물 상태  //
    const [board, setBoard] = useState<Board | null>(null);
    //  state:  more 버튼 상태  //
    const [showMore, setShowMore] = useState<boolean>(false);

    //  function:  작성일 포멧 변경 함수  //
    const getWriteDatetimeFormat = () => {
      if (!board) return '';
      const date = dayjs(board.writeDatetime);
      return date.format('YYYY.MM.DD hh:mm');
    }

    //  function:  get board response 처리 함수  //
    const getBoardResponse = (responseBody: GetBoardResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물입니다.')
      if(code === 'DBE') alert('데이터베이스 오류입니다.')
      if(code !== 'SU'){ 
        navigator(MAIN_PATH());
        return;
      }
      const board: Board = { ...responseBody as GetBoardResponseDto }
      setBoard(board);

      if(!loginUser){
        setIsWriter(false);
        return;
      }
      const isWriter = loginUser.email === board.writerEmail;
      setIsWriter(isWriter);
    }
    //  function: delete board 처리 함수  //
    const deleteBoardResponse = (responseBody: DeleteBoardResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;

      if(code === 'VF') alert('잘못된 접근입니다.');
      if(code === 'NU') alert('존재하지 않는 유저입니다.');
      if(code === 'NB') alert('존재하지 않는 게시물입니다.');
      if(code === 'AF') alert('인증에 실패했습니다.');
      if(code === 'NP') alert('권한이 없습니다.');
      if(code === 'DBE') alert('데이터베이스 오류입니다.');

      navigator(MAIN_PATH());
    }

    //  event handler: 닉네임 버튼 클릭 이벤트 처리  //
    const onNicknameClickHandler = () => {
    if(!board) return;
    navigator(USER_PATH(board.writerEmail));
    }
    //  event handler: more 버튼 클릭 이벤트 처리  //
    const onMoreButtonClickHandler = () => {
      setShowMore(!showMore);
    }
    //  event handler:  게시물 수정 버튼 클릭 이벤트 처리  //
    const onUpdateButtonClickHandler = () => {
      if(!board) return;
      if(!loginUser) return;
      if( loginUser.email !== board.writerEmail ) return;
      navigator(BOARD_PATH()+'/'+BOARD_UPDATE_PATH(board.boardNumber));
    }

    //  event handler:  게시물 삭제 버튼 클릭 이벤트 처리  //
    const onDeleteButtonClickHandler = () => {
      if(!board || !loginUser || !boardNumber) return;
      if(loginUser.email !== board.writerEmail ) return;
      DeleteBoardRequest(boardNumber, cookies.accessToken).then(deleteBoardResponse);
    }

    //  effect:  게시물 번호 path variable이 바뀔때 마다 게시물 불러오기  //
    useEffect(()=>{
      if(!boardNumber) {
       navigator(MAIN_PATH());
       return;
      }
      getBoardRequest(boardNumber).then(getBoardResponse);
     },[boardNumber]);

    //  render:  게시물 상세 상단 컴포넌트 렌더링  //
    if (!board) return <></>
    return(
      <div id='board-detail-top'>
        <div className='board-detail-header'>
          <div className='board-detail-title'>{board.title}</div>
          <div className='board-detail-top-sub-box'>
            <div className='board-detail-write-info-box'>
              <div className='board-detail-writer-profile-image' style={{backgroundImage: `url(${board.writerProfileImage ? board.writerProfileImage : defaultProfileImage})`}}></div>
              <div className='board-detail-writer-nickname' onClick={onNicknameClickHandler}>{board.writerNickname}</div>
              <div className='board-detail-info-divider'>{'\|'}</div>
              <div className='board-detail-write-date'>{getWriteDatetimeFormat()}</div>
            </div>
            {isWriter &&
            <div className='icon-button' onClick={onMoreButtonClickHandler}>
              <div className='icon more-icon'></div>
            </div>
            }
            {showMore &&
            <div className='board-detail-more-box'>
              <div className='board-detail-update-button' onClick={onUpdateButtonClickHandler}>{'수정'}</div>
              <div className='divider'></div>
              <div className='board-detail-delete-button' onClick={onDeleteButtonClickHandler}>{'삭제'}</div>
            </div>
            }
          </div>
        </div>
        <div className='divider'></div>
        <div className='board-detail-top-main'>
          <div className='board-detail-main-text'>{board.content}</div>
          {board.boardImageList.map((image, index) => <img className='board-detail-main-image' key={index} src={image}></img>) }
          
        </div>
      </div>
    );
  }

  // component:  게시물 상세 화면 하단 컴포넌트
  const BoardDetailBottom = () => {
    
    //  state:  댓글 textarea 참조 상태  //
    const commentRef = useRef<HTMLTextAreaElement | null>(null);
    //  state:  페이지네이션 관련 상태  //
    const {
      currentPage,currentSection, viewList, viewPageList, totalSection,
      setCurrentPage, setCurrentSection, setTotalList
     } = usePagination<CommentListItem>(5);
    //  state:  좋아요 리스트 상태  //

    const [favoriteList, setFavoriteList] = useState<FavoriteListItem[]>([]);
    //  state:  좋아요 상태  //
    const [isFavorite, setFavorite] = useState<boolean> (false);
    //  state:  좋아요 상자  보기 상태  //
    const [showFavorite, setShowFavorite] = useState<boolean> (false);
    //  state:  전체 댓글 개수 상태
    const [totalCommentCount, setTotalCommentCount] = useState<number>(0);
    //  state:  댓글 상자 보기 상태  //
    const [showComment, setShowComment] = useState<boolean> (true);
    //  state:  댓글 상태  //
    const [comment, setComment] = useState<string>('');
    
    

    //  function: get favorite list response 처리 함수  //
    const getFavoriteListResponse = (responseBody: GetFavoriteResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물입니다.');
      if(code === 'DBE') alert('데이터베이스 오류입니다.');
      if(code !== 'SU') return;
      
      const { favoriteList } = responseBody as GetFavoriteResponseDto;
      setFavoriteList(favoriteList);

      if(!loginUser) {
        setFavorite(false);
        return;
      }
      //  좋아요 누른 유저면 fill-in 버튼 표시
      const isFavorite = favoriteList.findIndex(favorite => favorite.email === loginUser.email) !== -1; 
      setFavorite(isFavorite);
    }

    //  function: get Comment list response 처리 함수  //
    const getCommentListResponse = (responseBody: GetCommentListResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'NB') alert('존재하지 않는 게시물입니다.');
      if(code === 'DBE') alert('데이터베이스 오류입니다.');
      if(code !== 'SU') return;
      
      const { commentList } = responseBody as GetCommentListResponseDto;
      setTotalList(commentList);
      setTotalCommentCount(commentList.length);
    }
    //  function: put favorite response 처리 함수  //
    const putFavoriteResponse = (responseBody: PutFavoriteResponseDto | ResponseDto | null) => {
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'VF') alert('잘못된 접근입니다.');
      if(code === 'NU') alert('존재하지 않는 유저 입니다.')
      if(code === 'NB') alert('존재하지 않는 게시물 입니다.')
      if(code === 'NB') alert('인증에 실패했습니다')
      if(code === 'DBE') alert('데이터베이스 오류입니다.')
      if(code !== 'SU') return;
      if(!boardNumber) return;

      getFavoriteListRequest(boardNumber).then(getFavoriteListResponse);
    }
    //  function:  post comment response 처리 함수  //
    const postCommentResponse = (responseBody: PostCommentResponseDto | ResponseDto | null) =>{
      if(!responseBody) return;
      const { code } = responseBody;
      if(code === 'VF') alert('잘못된 접근입니다.');
      if(code === 'NU') alert('존재하지 않는 유저 입니다.')
      if(code === 'NB') alert('존재하지 않는 게시물 입니다.')
      if(code === 'NB') alert('인증에 실패했습니다')
      if(code === 'DBE') alert('데이터베이스 오류입니다.')
      if(code !== 'SU') return;
      if(!boardNumber) return;
      
      setComment('');
      getCommentListRequest(boardNumber).then(getCommentListResponse);
    }
    //  event handler:  좋아요 클릭 이벤트 처리  //
    const onFavoriteClickHandler = () => {
      if(!loginUser || !cookies.accessToken || !boardNumber) return;
      PutFavoriteRequest(boardNumber, cookies.accessToken).then(putFavoriteResponse);
    }
    //  event handler:  좋아요 상자 보기 이벤트 처리  //
    const onShwoFavoriteClickHandler = () => {
      setShowFavorite(!showFavorite);
    }
    //  event handler:  댓글 상자 보기 이벤트 처리  //
    const onShowCommentClickHandler = () => {
      setShowComment(!showComment);
    }
    //  event handler:  댓글 작성 버튼 클릭 이벤트 처리  //
    const onCommentSubmitButtonClickHandler = () => {
      if(!comment || !boardNumber || !loginUser || !cookies.accessToken) return;
      const requestBody :PostCommentRequestDto = { content: comment };
      PostCommentRequest(boardNumber, requestBody, cookies.accessToken).then(postCommentResponse);
    }
    //  event handler:  댓글 변경 이벤트 처리  //
    const onCommentChangeHandler = (event: ChangeEvent<HTMLTextAreaElement>) => {
      const { value } = event.target;
      setComment(value);
      if(!commentRef.current) return;
      commentRef.current.style.height = 'auto';
      commentRef.current.style.height = `${commentRef.current.scrollHeight}px`;
    }
    
    //  effect:  게시물 번호 path variable 이 바뀔 때 마다 좋아요 및 댓글 리스트 불러오기  //
    useEffect(()=>{
     if(!boardNumber) return;
     getFavoriteListRequest(boardNumber).then(getFavoriteListResponse);
     getCommentListRequest(boardNumber).then(getCommentListResponse);
    },[boardNumber]);

    //  render:  게시물 상세 하단 컴포넌트 렌더링  //
    return(
     <div id='board-detail-bottom'>
      <div className='board-detail-bottom-button-box'>
        <div className='board-detail-bottom-button-group'>
          <div className='icon-button' onClick={onFavoriteClickHandler}>
            {isFavorite ?
            <div className='icon favorite-fill-icon'></div> :
            <div className='icon favorite-light-icon'></div>
            }
          </div>
          <div className='board-detail-bottom-button-text'>{`좋아요 ${favoriteList.length}`}</div>
          <div className='icon-button' onClick={onShwoFavoriteClickHandler}>
            {showFavorite ? 
            <div className='icon up-light-icon'></div> :
            <div className='icon down-light-icon'></div>
            }
          </div>
        </div>
        <div className='board-detail-bottom-button-group'>
          <div className='icon-button'>
            <div className='icon comment-icon'></div>
          </div>
          <div className='board-detail-bottom-button-text'>{`댓글 ${totalCommentCount}`}</div>
          <div className='icon-button' onClick={onShowCommentClickHandler}>
          {showComment ? 
            <div className='icon up-light-icon'></div> :
            <div className='icon down-light-icon'></div>
          }
          </div>
        </div>
      </div>
      {showFavorite &&
      <div className='board-detail-bottom-favorite-box'>
        <div className='board-detail-bottom-favorite-container'>
          <div className='board-detail-bottom-favorite-title'>{'좋아요 '}<span className='emphasis'>{favoriteList.length}</span></div>
          <div className='board-detail-bottom-favorite-contents'>
            {favoriteList.map((item) => <FavoriteItem favoriteListItem={item}/>)}
          </div>
        </div>
      </div>
      }
      {showComment &&
      <div className='board-detail-bottom-comment-box'>
        <div className='board-detail-bottom-comment-container'>
          <div className='board-detail-bottom-comment-title'>{'댓글 '}<span className='emphasis'>{totalCommentCount}</span></div>
          <div className='board-detail-bottom-comment-list-container'>
            {viewList.map((item,index) => <CommentItem key={index} commentListItem={item}/>)}
          </div>
        </div>
        <div className='divider'></div>
        <div className='board-detail-bottom-comment-pagination-box'>
          <Pagination
          currentPage={currentPage}
          currentSection={currentSection}
          viewPageList={viewPageList}
          totalSection={totalSection}
          setCurrentPage={setCurrentPage}
          setCurrentSection={setCurrentSection}
           />
        </div>
        {loginUser && 
        <div className='board-detail-bottom-comment-input-box'>
          <div className='board-detail-bottom-comment-input-container'>
            <textarea className='board-detail-bottom-comment-textarea' ref={commentRef} placeholder='댓글을 작성해주세요.'value={comment} onChange={onCommentChangeHandler}/>
            <div className='board-detail-bottom-comment-button-box'>
              <div className={comment === '' ? 'disable-button' : 'black-button'} onClick={onCommentSubmitButtonClickHandler}>{'댓글달기'}</div>
            </div>
          </div>
        </div>
        }
      </div>
      }
     </div>
    )
  }

  // effect:  게시물 번호 path variable 바뀔때 마다 게시물 조회수 증가  //
  let effectFlag = true;
  useEffect(()=>{
    if(!boardNumber) return;
    if(effectFlag){
      effectFlag = false;
      return;
    }
    IncreaseViewCountRequest(boardNumber).then(increaseViewCountResponse);
  },[boardNumber])

  // render: 게시물 상세 화면 컴포넌트 렌더링//
  return (
    <div id='board-detail-wrapper'>
      <div className='board-detail-container'>
        <BoardDetailTop/>
        <BoardDetailBottom/>
      </div>
    </div>
  )
}


