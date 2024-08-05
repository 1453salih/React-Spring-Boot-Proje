package salihkorkmaz.proje_d3.auth.token;

import org.springframework.stereotype.Service;
import salihkorkmaz.proje_d3.auth.dto.Credentials;
import salihkorkmaz.proje_d3.user.User;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements TokenService {

    @Override
    public Token createToken(User user, Credentials creds) {

        String emailColonPassword = creds.email() + ":" + creds.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic",token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }
}
