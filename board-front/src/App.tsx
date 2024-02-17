import React, { useState } from 'react';

import './App.css';
import BoardItem from 'components/BoardItem';
import { favoriteListMock, commentListMock, latestBoardListMock, top3BoardListMock } from 'mocks';
import Top3Item from 'components/Top3Item';
import CommentItem from 'components/CommentItem';
import CommentListItem from 'types/enum/interface/board-list-item.interface';
import FavoriteItem from 'components/FavoriteItem';
import InputBox from 'components/inputBox';

function App() {

  const [value, setValue] = useState<string>('');

  return (
    <>
      <InputBox label='이메일' type='text' placeholder='이메일 주소를 입력해 주세요' value={value} error={false} setValue={setValue} message='이메일은 8자이상'/>
    </>
  );
   

}

export default App;
