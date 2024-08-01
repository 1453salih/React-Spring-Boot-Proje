import {useTranslation} from "react-i18next";

export function LanguageSelector(){
    const {i18n} = useTranslation();
    const onSelectLanguage = (language) => {
        i18n.changeLanguage(language);
        localStorage.setItem("lang", language);
    }
    return(
        <>
            <img
                role="button"
                src="https://flagcdn.com/24x18/tr.png"
                srcSet="https://flagcdn.com/48x36/tr.png 2x,
    https://flagcdn.com/72x54/tr.png 3x"
                width="24"
                height="18"
                alt="Turkce"
                onClick={()=>onSelectLanguage('tr')}
            ></img>

            <img
                role="button"
                src="https://flagcdn.com/24x18/us.png"
                srcSet="https://flagcdn.com/48x36/tr.png 2x,
    https://flagcdn.com/72x54/tr.png 3x"
                width="24"
                height="18"
                alt="English"
                onClick={()=>onSelectLanguage('en')}
            ></img>
        </>

    )
}