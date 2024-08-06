import React, { useEffect, useState} from 'react';
import {useTranslation} from "react-i18next";
import {Alert} from "@/shared/components/Alert.jsx";
import {Input} from "@/shared/components/Input.jsx";
import {Button} from "@/shared/components/Button.jsx";
import {login} from "@/pages/Login/api.js";
import {AuthContext, useAuthDispatch} from "@/shared/state/context.jsx";
import {useNavigate} from "react-router-dom";

export function Login() {
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [apiProgress, setApiProgress] = useState(false);
    const [errors, setErrors] = useState({});
    const [generalError, setGeneralError] = useState();
    const {t} = useTranslation();
    const navigate = useNavigate();
    const dispatch = useAuthDispatch();

    //* İnput alanında herhangi bir değişiklik olduğunda hata mesajını sıfırlar
    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                email: undefined
            }
        });
    }, [email]);
    useEffect(() => {
        setErrors((lastErrors) => ({
            ...lastErrors,
            password: undefined,
        }));
    }, [password]);

    const onSubmit = async (event) => {
        event.preventDefault();
        setGeneralError();
        setApiProgress(true);
        try {
            const response = await login({email,password})
            dispatch({type:'login-success',data:response.data.user});
            navigate("/")
        } catch (axiosError) {
            if (axiosError.response?.data) { //* Yanıt aldıysak ve data varsa
                if (axiosError.response.data.status === 400) {              //* 400 cevanı aldıysak ValidationsError
                    setErrors(axiosError.response.data.validationErrors);
                } else {
                    setGeneralError(axiosError.response.data.message);
                }
            } else {
                setGeneralError(t("genericError"));
            }
        } finally {
            setApiProgress(false)
        }
    };

    return (
        <div className="container mt-5">
            <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
                <form className="card" onSubmit={onSubmit}>
                    <div className="text-center card-header">
                        <h1>{t('login')}</h1>
                    </div>
                    <div className="card-body">

                        <Input id="email" label={t('email')} error={errors.email}
                               onChange={(event) => setEmail(event.target.value)}/>
                        <Input id="password" label={t('password')} error={errors.password}
                               onChange={(event) => setPassword(event.target.value)} type="password"/>
                        {generalError && (
                            <Alert styleType="danger">{generalError}</Alert>
                        )}
                        <div className="text-center">
                            <Button
                                disabled={!email || !password}
                                apiProgress={apiProgress}
                            >
                                {t('login')}
                            </Button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}