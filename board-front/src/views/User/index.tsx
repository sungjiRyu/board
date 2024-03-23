import React, { useEffect, useRef, useState } from 'react'
import './style.css'
import defaultProfileImage from 'assets/image/default-profile-image.png';
import { useParams } from 'react-router-dom';
// component: 유저 화면 컴포넌트 //
export default function User() {

  //  state:  마이페이지 여부 상태  //
  const { userEmail } = useParams();

  // component: 유저 화면 상단 컴포넌트 //
  const UserTop = () => {

    // state:  이미지 파일 인풋 참조 상태  //
    const imageInputRef = useRef<HTMLInputElement | null>(null);
    // state:  마이페이지 여부 상태  //
    const [isMyPage, setMyPage] = useState<boolean>(true);
    // state:  닉네임 상태  //
    const [nickname, setNickname] = useState<string>('');
    // state:  닉네임 변경 여부 상태  //
    const [isNicknameChange, setNicknameChange] = useState<Boolean>(false);
    // state:  변경 닉네임 상태  //
    const [changeNickname, setChangeNickname] = useState<string>('');
    // state:  프로필 이미지 상태  //
    const [profileImage, setProfileImage] = useState<string | null>(null);

    // effect: email path varible 변경시 실행 할 함수
    useEffect(() => {

      if(!userEmail) return;
      setNickname('닉네임');
      setProfileImage('https://img.khan.co.kr/news/2023/01/02/news-p.v1.20230102.1f95577a65fc42a79ae7f990b39e7c21_P1.webp')
    }, [userEmail]);
  
    // render: 유저 화면 상단 컴포넌트 렌더링//
    return(
      <div id='user-top-wrapper'>
        <div className='user-top-container'>
          {isMyPage ?
          <div className='user-top-my-profile-image-box'>
            {profileImage !== null ?
            <div className='user-top-profile-image' style={{backgroundImage : `url(${profileImage})`}}></div> :
            <div className='user-top-my-profile-image-nothing-box'>
              <div className='icon-box-large'>
                <div className='image-box-white-icon'></div>
              </div>
            </div>
            }
            <input ref={imageInputRef} type='file' accept ='image/*' style={{display:'none'}}/>
          </div> :
          <div className='user-top-profile-image-box'>
            <div className='user-top-profile-image' style={{backgroundImage : `url(${profileImage ? profileImage :  defaultProfileImage})`}}></div>
          </div>
          }
          <div className='user-top-info-box'>
            <div className='user-top-info-nickname-box'>
              {isMyPage ?
              <>
              {isNicknameChange ?
              <input className='user-top-info-nickname-input' type='text' size={nickname.length + 1} value={changeNickname} />  :
              <div></div>
              }
              <div className='user-top-info-nickname'>{nickname}</div>
              <div className='icon-button'>
                <div className='icon edit-icon'></div>
              </div>
              </> :
              <div className='user-top-info-nickname'>{nickname}</div>
              }
            </div>
            <div className='user-top-info-email'>{userEmail}</div>
          </div>
        </div>
      </div>
    );
  };

   // component: 유저 화면 하단 컴포넌트 //
   const UserBottom = () => {
    
    // render: 유저 화면 하단 컴포넌트 렌더링//
    return(
      <div></div>
    );
  };

  // render: 유저 화면 컴포넌트 렌더링//
  return (
    <>
     <UserTop/> 
     <UserBottom/> 
    </>
  );
};
