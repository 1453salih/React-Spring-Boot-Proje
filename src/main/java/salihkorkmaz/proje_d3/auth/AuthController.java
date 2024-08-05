package salihkorkmaz.proje_d3.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import salihkorkmaz.proje_d3.auth.dto.AuthResponse;
import salihkorkmaz.proje_d3.auth.dto.Credentials;
import salihkorkmaz.proje_d3.auth.exception.AuthenticationException;
import salihkorkmaz.proje_d3.error.ApiError;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/api/v1/auth")
    AuthResponse handleAuthentication(@RequestBody Credentials creds){
        return authService.authenticate(creds);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<?> handleAuthenticationException(AuthenticationException exception){
        ApiError error = new ApiError();
        error.setPath("/api/v1/auth");
        error.setStatus(401);
        error.setMessage(exception.getMessage());
        return ResponseEntity.status(401).body(error);
    }
}