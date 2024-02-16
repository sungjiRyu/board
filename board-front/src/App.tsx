import React from 'react';

import './App.css';
import BoardItem from 'components/BoardItem';
import { favoriteListMock, commentListMock, latestBoardListMock, top3BoardListMock } from 'mocks';
import Top3Item from 'components/Top3Item';
import CommentItem from 'components/CommentItem';
import CommentListItem from 'types/enum/interface/board-list-item.interface';
import FavoriteItem from 'components/FavoriteItem';

function App() {
  return (
    <>
      <div style={{display:'flex', columnGap: '30px', rowGap: '20px' }}>
        {favoriteListMock.map(favoriteListItem => <FavoriteItem favoriteListItem = {favoriteListItem}/>)}
      </div>
    </>
  );
   

}

export default App;
