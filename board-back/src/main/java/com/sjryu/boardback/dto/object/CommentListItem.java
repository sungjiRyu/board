package com.sjryu.boardback.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


import com.sjryu.boardback.repository.resultSet.GetCommentListResultSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {
    private String nickname;
    private String profileImage;
    private String writerDatetime;
    private String content;

    public CommentListItem(GetCommentListResultSet resultSet){
        this.nickname = resultSet.getUserNickname();
        this.profileImage = resultSet.getUserProfileImg();
        this.writerDatetime = resultSet.getCommentWriteDatetime();
        this.content = resultSet.getCommentContent();
    }

    public static List<CommentListItem> copyList(List<GetCommentListResultSet> resultSets) {
        List<CommentListItem> list = new ArrayList<>();

        for(GetCommentListResultSet result : resultSets){
            CommentListItem commentListItem = new CommentListItem(result);
            list.add(commentListItem);
        }
        return list;
    }
}
