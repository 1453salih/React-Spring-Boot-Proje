package salihkorkmaz.proje_d3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import salihkorkmaz.proje_d3.auth.token.TokenService;
import salihkorkmaz.proje_d3.shared.GenericMessage;
import salihkorkmaz.proje_d3.shared.Messages;
import salihkorkmaz.proje_d3.user.dto.UserCreate;
import salihkorkmaz.proje_d3.user.dto.UserDTO;



@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user){
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("salihkorkmaz.create.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }
    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token){
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("salihkorkmaz.activate.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/api/v1/users")
    Page<UserDTO> getAllUsers(Pageable page,@RequestHeader(name = "Authorization",required = false)String authorizationHeader ){
        var loggedInUser = tokenService.verifyToken(authorizationHeader);
        return userService.getUsers(page,loggedInUser).map(UserDTO::new);
    }

    @GetMapping("/api/v1/users/{id}")
    UserDTO getUserById(@PathVariable long id){
        return new UserDTO(userService.getUser(id));
    }

}



