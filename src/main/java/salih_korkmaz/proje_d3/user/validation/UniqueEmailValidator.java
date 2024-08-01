package salih_korkmaz.proje_d3.user.validation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import salih_korkmaz.proje_d3.user.User;
import salih_korkmaz.proje_d3.user.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User inDB = userRepository.findByEmail(value);
        return inDB == null;
    }

}