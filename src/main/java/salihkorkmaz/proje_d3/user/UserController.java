package salihkorkmaz.proje_d3.user;

import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; 
import salihkorkmaz.proje_d3.error.ApiError;
import salihkorkmaz.proje_d3.shared.GenericMessage;
import salihkorkmaz.proje_d3.shared.Messages;
import salihkorkmaz.proje_d3.user.dto.UserCreate;
import salihkorkmaz.proje_d3.user.dto.UserDTO;
import salihkorkmaz.proje_d3.user.exception.ActivationNotificationExcepiton;
import salihkorkmaz.proje_d3.user.exception.InvalidTokenException;
import salihkorkmaz.proje_d3.user.exception.NotFoundException;
import salihkorkmaz.proje_d3.user.exception.NotUniqueEmailException;



@RestController
public class UserController {

    @Autowired
    UserService userService;

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
    Page<UserDTO> getAllUsers(Pageable page){
        return userService.getUsers(page).map(UserDTO::new);
    }

    @GetMapping("/api/v1/users/{id}")
    UserDTO getUserById(@PathVariable long id){
        return new UserDTO(userService.getUser(id));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception){
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        String message = Messages.getMessageForLocale("salihkorkmaz.error.validation", LocaleContextHolder.getLocale());
        apiError.setMessage(message);
        apiError.setStatus(400);
        var validationErrors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> handleNotUniqueEmailEx(NotUniqueEmailException exception){
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(ActivationNotificationExcepiton.class)
    ResponseEntity<ApiError> handleActivationNotificationExcepiton(ActivationNotificationExcepiton exception){
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(502);
        return ResponseEntity.status(502).body(apiError);
    }
    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException exception, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception, HttpServletRequest request){
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(404);
        return ResponseEntity.status(404).body(apiError);
    }

}



