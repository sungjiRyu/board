import { BoardListItem } from 'types/enum/interface';
import ResponseDto from '../response.dto';

export default interface GetUserBoardListResponseDto extends ResponseDto {
    userBoardList: BoardListItem[];
}