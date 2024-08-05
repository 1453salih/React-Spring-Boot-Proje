package salihkorkmaz.proje_d3.auth.dto;

import salihkorkmaz.proje_d3.auth.token.Token;
import salihkorkmaz.proje_d3.user.dto.UserDTO;

public class AuthResponse {

    Token token;

    UserDTO user;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
