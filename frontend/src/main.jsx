import React from 'react'
import ReactDOM from 'react-dom/client'
import "./styles.scss"
import "./locales";

import {RouterProvider} from "react-router-dom"
import router from "./router/index.js"






ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <RouterProvider router={router}/>
      {/*<BrowserRouter>*/}
      {/*    <Routes>*/}
      {/*        <Route path="/" element={<Home/>} />*/}
      {/*        <Route path="/signup" element={<div>Home Page</div>} />*/}
      {/*    </Routes>*/}
      {/*</BrowserRouter>*/}
  </React.StrictMode>,
)
