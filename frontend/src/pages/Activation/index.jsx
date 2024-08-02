import {useParams} from "react-router-dom";

export function Activation() {
    const {token} = useParams()
    return <div><h1>Activation Page</h1></div>
}