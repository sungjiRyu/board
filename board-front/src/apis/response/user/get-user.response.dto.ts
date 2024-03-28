import { User } from "types/enum/interface";
import ResponseDto from "../response.dto";

export default interface GetUserResponseDto extends ResponseDto, User {
    
}