import React, {useEffect, useMemo, useState} from 'react';
import {singUp} from "./api.js";
import {Input} from "./components/Input.jsx";
import {useTranslation} from "react-i18next";
import {LanguageSelector} from "../shared/components/LanguageSelector.jsx";


export function SignUp() {

    const [username, setUsername] = useState();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [passwordRepeat, setPasswordRepeat] = useState();
    const [apiProgress, setApiProgress] = useState(false);
    const [successMessage, setSuccessMessage] = useState();
    const [errors, setErrors] = useState({});
    const [generalError, setGeneralError] = useState();
    const { t } = useTranslation()

    //* İnput alanında herhangi bir değişiklik olduğunda hata mesajını sıfırlar
    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                username: undefined
            }
        });
    }, [username]);

    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                email: undefined
            }
        });
    }, [email]);

    const onSubmit = async (event) => {
        event.preventDefault()
        setApiProgress(true)
        setGeneralError();
        setSuccessMessage();
        try {
            const response = await singUp({
                username,
                email,
                password
            })
            setSuccessMessage(response.data.message)
        } catch (axiosError) {
            if (axiosError.response?.data) { //* Yanıt aldıysak ve data varsa
                if (axiosError.response.data.status === 400){              //* 400 cevanı aldıysak ValidationsError
                    setErrors(axiosError.response.data.validationErrors);
                }else{
                    setGeneralError(axiosError.response.data.message);
                }
            } else {
                setGeneralError(t('genericError'));
            }
        } finally {
            setApiProgress(false)
        }
    };
    const passwordRepeatError = useMemo(() => {
        if (password && password !== passwordRepeat) {
            return t('passwordMissmatch');
        }
        return '';
    },[password,passwordRepeat])
    return (
        <div className="container mt-5">
            <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
                <form className="card" onSubmit={onSubmit}>
                    <div className="text-center card-header">
                        <h1>{t('signUp')}</h1>
                    </div>
                    <div className="card-body">
                        <Input id="username" label={t('username')} error={errors.username}
                               onChange={(event) => setUsername(event.target.value)}/>
                        <Input id="email" label={t('email')} error={errors.email}
                               onChange={(event) => setEmail(event.target.value)}/>
                        {/*<div className="mb-3">*/}
                        {/*    <label htmlFor="username" className="form-label">Username</label>*/}
                        {/*    <input*/}
                        {/*        id="username"*/}
                        {/*        className={errors.username ? "form-control is-invalid" : "form-control"}*/}
                        {/*        type="text"*/}
                        {/*        placeholder="Username"*/}
                        {/*        onChange={(event) => setUsername(event.target.value)}/>*/}
                        {/*    <div className="invalid-feedback">*/}
                        {/*        {errors.username}*/}
                        {/*    </div>*/}
                        {/*</div>*/}
                        {/*<div className="mb-3">*/}
                        {/*    <label htmlFor="email" className="form-label">E-mail</label>*/}
                        {/*    <input id="email" className="form-control" type="email" placeholder="E-mail"*/}
                        {/*           onChange={(event) => setEmail(event.target.value)}/>*/}
                        {/*</div>*/}
                        <Input id="password" label={t('password')} error={errors.password}
                               onChange={(event) => setPassword(event.target.value)} type="password"/>
                        <Input id="passwordRepeat" label={t('passwordRepeat')} error={passwordRepeatError}
                               onChange={(event) => setPasswordRepeat(event.target.value)} type="password"/>

                        {successMessage && (
                            <div className="alert alert-success">{successMessage}</div>
                        )}
                        {generalError && (
                            <div className="alert alert-danger">{generalError}</div>
                        )}
                        <div className="text-center">
                            <button className="btn btn-primary"
                                    disabled={!password || password !== passwordRepeat}>
                                {apiProgress &&
                                    <span className="spinner-border spinner-border-sm"
                                          aria-hidden="true"></span>}
                                {t('signUp')}
                            </button>
                        </div>
                    </div>
                </form>
                <LanguageSelector/>
            </div>
        </div>)
}