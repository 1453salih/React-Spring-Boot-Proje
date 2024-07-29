package salih_korkmaz.proje_d3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import salih_korkmaz.proje_d3.shared.GenericMessage;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@RequestBody User user) {
        userService.save(user);
        return new GenericMessage("User is created");
    }
}




