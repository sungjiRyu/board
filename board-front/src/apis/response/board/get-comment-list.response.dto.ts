import { CommentListItem } from "types/enum/interface";
import ResponseDto from "../response.dto";

export default interface GetCommentListResponseDto extends ResponseDto {
    commentList : CommentListItem[];
}