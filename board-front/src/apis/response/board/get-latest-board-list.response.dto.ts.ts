import { BoardListItem } from 'types/enum/interface';
import ResponseDto from '../response.dto';

export default interface GetLatestBoardListResponseDto extends ResponseDto {
    latestList: BoardListItem[];
}