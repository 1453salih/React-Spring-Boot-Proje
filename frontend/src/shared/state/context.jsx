import {createContext, useContext, useEffect, useReducer,useState} from "react";
import {loadAuthState, storeAuthState} from "@/shared/state/storage.js";
import {setToken} from "@/lib/http.js";
export const AuthContext = createContext();

export const AuthDispatchContext = createContext();

export function useAuthState(){
    return useContext(AuthContext);
}

export function useAuthDispatch(){
    return useContext(AuthDispatchContext);
}

const authReducer = (authState, action) => {
    switch (action.type) {
        case 'login-success':
            setToken(action.data.token);
            return action.data.user;
        case 'logout-success':
            setToken();
            return {id: 0}
        default:
            throw new Error(`Unknown action type "${action.type}"`);
    }
}

export function AuthenticationContext({children}) {
    const [authState, dispatch] = useReducer(authReducer, loadAuthState());

    useEffect(() => {
        storeAuthState(authState);
    }, [authState]);

    const [auth, setAuth] = useState(loadAuthState());

    const onLoginSuccess = (data) => {
        setAuth(data);
        storeAuthState(data);
    };

    const onLogoutSuccess = () => {
        setAuth({id: 0})
        storeAuthState({id: 0})
    }

    return <AuthContext.Provider value={authState}>
        <AuthDispatchContext.Provider value={dispatch}>
            {children}
        </AuthDispatchContext.Provider>
    </AuthContext.Provider>
}