import React from 'react'
import './style.css';
import { BoardListItem } from 'types/enum/interface';

interface Props {
    boardListItem: BoardListItem
}

// component: Board List Item 컴포넌트 //
export default function BoardListItem({ boardListItem }: Props) {

    // Properties //
    const { boardNumber, title, content, boardTitleImage } = boardListItem;
    const { favoriteCount, commentCount, viewCount } = boardListItem;
    const { writeDatetime, writerNickname, writerProfileImage } = boardListItem;
    

// render: Board List Item 컴포넌트 렌더링//
  return (
    <div className='board-list-item'>
        <div className='board-list-item-main-box'>
            <div className='board-list-item-top'>
                <div className='board-list-item-profile-box'>
                    <div className='board-list-item-profile-image' style={{backgroundImage: 'url(https://d1bg8rd1h4dvdb.cloudfront.net/upload/imgServer/storypick/editor/2019052111361692611.jpg)'}}></div>
                </div>
                <div className='board-list-item-write-box'>
                    <div className='board-list-item-nickname'>{writerNickname}</div>
                    <div className='board-list-item-write-date'>{writeDatetime}</div>
                </div>
            </div>
            <div className='board-list-item-middle'>
                <div className='board-list-item-title'>{title}</div>    
                <div className='board-list-item-content'>{content}</div>    
            </div>
            <div className='board-list-item-bottom'>
                <div className='board-list-item-counts'>
                    {`댓글0 • 좋아요0 • 조회수0`}
                </div>
            </div>
            <div className='board-list-item-image-box'>
                <div className='board-list-item-image' style={{backgroundImage: `url(https://images.mypetlife.co.kr/content/uploads/2023/11/27170717/AdobeStock_306960579-scaled.jpeg)`}}></div>
            </div>
        </div>
        
        
    </div>
  )
}

