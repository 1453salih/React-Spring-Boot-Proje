import React from "react";



export function Input(props) {
    const {id, label, error, onChange} = props;
    return (
        <div className="mb-3">
            <label htmlFor={id} className="form-label">
                {label}
            </label>
            <input
                id={label}
                className={error ? "form-control is-invalid" : "form-control"}
                onChange={onChange}
            />
            <div className="invalid-feedback">
                {error}
            </div>
        </div>
    );
}