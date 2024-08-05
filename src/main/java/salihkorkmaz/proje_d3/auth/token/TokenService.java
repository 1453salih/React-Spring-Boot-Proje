package salihkorkmaz.proje_d3.auth.token;

import salihkorkmaz.proje_d3.auth.dto.Credentials;
import salihkorkmaz.proje_d3.user.User;

public interface TokenService {

    public Token createToken(User user, Credentials creds);

    public User verifyToken(String authorizationHeader);

}