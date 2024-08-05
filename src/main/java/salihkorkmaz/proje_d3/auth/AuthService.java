package salihkorkmaz.proje_d3.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import salihkorkmaz.proje_d3.auth.dto.AuthResponse;
import salihkorkmaz.proje_d3.auth.dto.Credentials;
import salihkorkmaz.proje_d3.auth.exception.AuthenticationException;
import salihkorkmaz.proje_d3.auth.token.Token;
import salihkorkmaz.proje_d3.auth.token.TokenService;
import salihkorkmaz.proje_d3.user.User;
import salihkorkmaz.proje_d3.user.UserService;
import salihkorkmaz.proje_d3.user.dto.UserDTO;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {
        User inDB = userService.findByEmail(creds.email());
        if (inDB == null) throw new AuthenticationException();
        if(!passwordEncoder.matches(creds.password(), inDB.getPassword())) throw new AuthenticationException(); //Eşleşmiyorsa Exception döner.

        Token token = tokenService.createToken(inDB ,creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));
        return authResponse;
    }
}
