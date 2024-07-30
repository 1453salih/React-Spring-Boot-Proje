package salih_korkmaz.proje_d3.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import salih_korkmaz.proje_d3.error.ApiError;
import salih_korkmaz.proje_d3.shared.GenericMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {

        userService.save(user);
        return new GenericMessage("User is created");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException excepiton) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation error");
        apiError.setStatus(400);
        var validationErrors = excepiton.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage,(existing,replacing)->existing));
        apiError.setValidationErrors(validationErrors);
        return  ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> NotUniqueEmailEx(NotUniqueEmailException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation error");
        apiError.setStatus(400);
        Map<String,String> validationErrors = new HashMap<>();
        validationErrors.put("email","E-mail in use");
        apiError.setValidationErrors(validationErrors);
        return  ResponseEntity.badRequest().body(apiError);
    }

}




