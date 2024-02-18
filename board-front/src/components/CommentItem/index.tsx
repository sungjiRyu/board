import React from 'react'
import './style.css'
import { CommentListItem } from 'types/enum/interface'
import defultProfileImage from 'assets/image/default-profile-image.png'

interface Props {
    commentListItem: CommentListItem;
}

// component: component List Item 컴포넌트 //
export default function index({ commentListItem }: Props) {

    //  properties //
    const {nickname, profileImage, writeDatetime, content} = commentListItem;
  return (
    <div className='comment-list-item'>
        <div className='comment-list-item-top'>
            <div className='comment-list-item-profile-box'></div>
                <div className='comment-list-item-profile-image' style={{backgroundImage: `url(${profileImage !== null ? profileImage : defultProfileImage})`}}></div>
            <div className='comment-list-item-nickname'>{nickname}</div>
            <div className='comment-list-item-divider'>{'|'}</div>
            <div className='comment-list-item-time'>{writeDatetime+'분전'}</div>
        </div>
        <div className='comment-list-item-main'>
            <div className='comment-list-item-content'>{content}</div>
        </div>
    </div>
  )
}