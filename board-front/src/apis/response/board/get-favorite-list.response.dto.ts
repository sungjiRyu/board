import { FavoriteListItem } from "types/enum/interface";
import ResponseDto from "../response.dto";

export default interface GetFavoriteResponseDto extends ResponseDto {
    favoriteList: FavoriteListItem[];
}