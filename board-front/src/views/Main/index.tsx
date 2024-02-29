import React, { useEffect, useState } from 'react'
import './style.css'
import { latestBoardListMock, top3BoardListMock } from 'mocks'
import Top3Item from 'components/Top3Item'
import { BoardListItem } from 'types/enum/interface'
import BoardItem from 'components/BoardItem'
import Pagination from 'components/Pagination'
import { useNavigate } from 'react-router-dom'
import { SEARCH_PATH } from 'constant'

// component: 메인화면 화면 컴포넌트 //
export default function Main() {

  //  function:  네비게이트 함수  //
  const navigate = useNavigate();

  // component: 메인화면 상단 화면 컴포넌트 //
  const MaintTop = () => {

    //  state:  주간 top3 게시물 리스트 상태  //
    const [top3BoardList, setTop3BoardList] = useState<BoardListItem[]>([]);

    //  effect:  첫 마운트 시 실행될 함수  //
    useEffect(() => {
      setTop3BoardList(top3BoardListMock)
    },[])
    
    // render: 메인화면 상단 화면 컴포넌트 렌더링 //
    return(
      <div id = 'main-top-wrapper'>
        <div className='main-top-container'>
          <div className='main-top-title'>{'Ryus board에서 \n다양한 이야기를 나눠보세요'}</div>
          <div className='main-top-contents-box'>
            <div className='main-top-contents-title'>{'주간 TOP3 게시물'}</div>
            <div className='main-top-contents'>
              {top3BoardList.map(item => <Top3Item top3ListItem={item}/>)}
            </div>
          </div>
        </div>
      </div>
    )
  }

  // component: 메인화면 하단 화면 컴포넌트 //
  const MaintBottom = () => {

    // state:  최신 게시물 리스트 상태 (임시)  //
    const [ currentBoardList, setCurrentBoardList ] = useState<BoardListItem[]>([]);
    // state:  인기 검색어 리스트 상태   //
    const [ popularWrodList, setPopularWordList ] = useState<string[]>([]);

    //  event handler:  인기 검색어 클릭 이벤트 처리  //
    const onPopularWordClickHandler = (word:string) => {
      navigate(SEARCH_PATH(word));
    }

    //  effect:  첫 마운트 시 실행될 함수  //
    useEffect(() => {
      setCurrentBoardList(latestBoardListMock);
      setPopularWordList(['안녕', '잘가', '또봐']);
    },[])
    

    // render: 메인화면 하단 화면 컴포넌트 렌더링 //
    return(
      <div id='main-bottom-wrapper'>
        <div className='main-bottom-container'>
          <div className='main-bottom-title'>{'최신 게시물'}</div>
          <div className='main-bottom-contents-box'>
            <div className='main-bottom-current-contents'>
              {currentBoardList.map(item => <BoardItem boardListItem={item}/>)}
            </div>
            <div className='main-bottom-popular-box'>
              <div className='main-bottom-popular-card'>
                <div className='main-bottom-popular-card-container'>
                  <div className='main-bottom-popular-card-title'>{'인기 검색어'}</div>
                  <div className='main-bottom-popular-card-contents'>
                  {popularWrodList.map( word => <div className='word-badge' onClick={() => onPopularWordClickHandler(word)}>{word}</div> )}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className='main-bottom-pagination-box'>
            {/* <Pagination/> */}
          </div>
        </div>
      </div>
    )
  }

  // render: 메인화면 화면 컴포넌트 렌더링 //
  return (
    <div>
      <MaintTop/>
      <MaintBottom/>
    </div>
  )
}
