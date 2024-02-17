import { User } from 'types/enum/interface';
import { create } from 'zustand';

interface LoginUserStore {
    loginUser: User | null;
    setLoginUser: (loginUser: User) => void;
    resetLoginUser: () => void;
};


const useLoginUserStore = create<LoginUserStore>(set => ({
    loginUser: null,
    // loginUser 받아와서 null 값인(초기값) loginUser에 다시 집어넣음 
    setLoginUser: loginUser => set(state => ({...state, loginUser})),
    // 아무것도 받아오지 않고 loginUser 를 null로 바꿔줌  --> 로그아웃 할때 loginUser 값을 비워줄때 사용
    resetLoginUser: () => set(state => ({ ...state, loginUser: null}))
}));

export default useLoginUserStore;