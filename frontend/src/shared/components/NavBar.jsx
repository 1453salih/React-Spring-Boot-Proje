import {Link} from "react-router-dom";
import logo from "@/assets/logo.png";
import {useTranslation} from "react-i18next";
import {useContext} from "react";
import {AuthContext, AuthDispatchContext, useAuthDispatch, useAuthState} from "@/shared/state/context.jsx";

export function NavBar() {
    const {t} = useTranslation();
    const authState = useAuthState();
    const dispatch = useAuthDispatch();

    const onClickLogout = () =>{
        dispatch({type: 'logout-success'});
    }

    return (
        <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">
                    <img src={logo} alt="Logo" width={60}
                         className="d-inline-block align-text-top"/>
                    Bootstrap
                </Link>
                <ul className="navbar-nav">
                    {authState.id === 0 && (
                        <>
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">{t('login')}</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/signup">{t('singup')}</Link>
                            </li>
                        </>
                    )}
                    {authState.id > 0 && (
                        <>
                            <li className="nav-item">
                                <Link className="nav-link" to={`/user/${authState.id}`}>My Profile</Link>
                            </li>
                            <li className="nav-item">
                                <span className="nav-link" role="button" onClick={onClickLogout}>
                                    Logout
                                </span>
                            </li>
                        </>
                    )}
                </ul>
            </div>
        </nav>
    );
}