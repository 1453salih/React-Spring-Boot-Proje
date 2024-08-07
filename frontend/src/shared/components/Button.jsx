import {Spinner} from "./Spinner.jsx";
import React from "react";

export function Button({
                           apiProgress,
                           disabled,
                           children,
                           onClick,
                           styleType = 'primary',
                       }) {
    return (
        <button
            className={`btn btn-${styleType}`}
            disabled={apiProgress || disabled}
            onClick={onClick}
        >
            {apiProgress && <Spinner sm/>}
            {children}
        </button>
    );
}