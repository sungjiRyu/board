import axios from "axios";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import { SignInResponseDto } from "./response/auth";
import { ResponseDto } from "./response";

const DOMAIN = 'http://localhost:4001';

const API_DOMAIN = `${DOMAIN}/api/v1`;

// api 요청 url
const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`; 

// async 동기처리
export const signInRequest = async (requestBody: SignInRequestDto) => {
    // await 이동작을 기다리겠다
    const result = await axios.post(SIGN_IN_URL(), requestBody)
        .then(response => {
            const responseBody: SignInResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        })
    return result;
}

export const signUpRequest = async (requestBody: SignUpRequestDto) => {

}