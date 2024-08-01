package salihkorkmaz.proje_d3.user.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "{salihkorkmaz.constraint.email.notunique}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}