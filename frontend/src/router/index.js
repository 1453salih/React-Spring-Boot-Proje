import {createBrowserRouter} from "react-router-dom";
import {Home} from "../pages/Home/index.jsx";
import {SignUp} from "../pages/SingUp/index.jsx";
import App from "../App.jsx";
import {Activation} from "../pages/Activation/index.jsx";

export default createBrowserRouter([
    {
        path:"/",
        Component:App,
        children:[
            {
                path:"/",
                index: true,
                Component:Home,
            },
            {
                path:"/signup",
                Component:SignUp,
            },
            {
                path: "/activation/:token",
                Component:Activation

            }
        ]
    }

])