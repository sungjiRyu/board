import { BoardListItem } from "types/enum/interface";
import ResponseDto from "../response.dto";

export default interface GetSearchBoardListResponseDto extends ResponseDto {
    searchList: BoardListItem[];
}