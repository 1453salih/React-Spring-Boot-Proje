import {Link, Outlet} from "react-router-dom";
import logo from './assets/logo.png'
import React from "react";
import {LanguageSelector} from "./shared/components/LanguageSelector.jsx";
import {useTranslation} from "react-i18next";


function App() {
    const { t } = useTranslation();
    return (
        <>
            <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/">
                        <img src={logo} alt="Logo" width={60}
                             className="d-inline-block align-text-top"/>
                        Bootstrap
                    </Link>
                    <ul className="navbar-nav">
                        <li className="nav-item" >
                            <Link className="nav-link" to="/signup">{t('singup')}</Link>
                        </li>
                    </ul>
                </div>
            </nav>
            <div className="container mt-3">
                <Outlet/>
                <LanguageSelector/>
            </div>
        </>
    )
}

export default App
