import http from "../../lib/http"

export function singUp(body) {
    return http.post('api/v1/users', body);
}