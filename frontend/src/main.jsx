import React from 'react'
import ReactDOM from 'react-dom/client'
import "./styles.scss"

import {SignUp} from "./SingUp/index.jsx";
import "./locales";

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <SignUp/>
  </React.StrictMode>,
)
