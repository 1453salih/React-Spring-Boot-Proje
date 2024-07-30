import axios from "axios";

export function singUp(body) {
    return axios.post('api/v1/users', body)
}