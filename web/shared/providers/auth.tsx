import {createContext, FC, useState} from "react";
import {UserModel} from "../models/User";

type LoginFn = (handleOrEmail: string, password: string) => UserModel;
type IauthContext = {
    user: UserModel | null
    login: LoginFn;
} | null

const AuthContext = createContext<IauthContext>(null);

const AuthProvider: FC = ({children}) => {
    /*
    * responsible for:
    * authenticating
    * authorization
    * obtaining current user's info
    * */

    const [user, setUser] = useState<UserModel | null>(null);

    const login: LoginFn = () => {
        return {
            id: 'hi',
            handle: 'hello',
            email: 'ok'
        }
    }

    return (
        <AuthContext.Provider value={{user, login}}>
            {children}
        </AuthContext.Provider>
    );
}