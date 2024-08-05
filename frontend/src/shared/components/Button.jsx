import {Spinner} from "./Spinner.jsx";
import React from "react";

export function Button({apiProgress, disabled, children}){
    return (
        <button className="btn btn-primary"
                disabled={apiProgress || disabled}>
            {apiProgress && <Spinner sm/>}
            {children}
        </button>
    );
}