import { User } from 'types/enum/interface';
import ResponseDto from '../response.dto';

export default interface GetSignInUserResponseDto extends ResponseDto, User {
    
}