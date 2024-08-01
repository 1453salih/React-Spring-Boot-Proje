package salih_korkmaz.proje_d3.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import salih_korkmaz.proje_d3.user.User;
import salih_korkmaz.proje_d3.user.validation.UniqueEmail;

public record UserCreate(
        @NotBlank(message = "{salih_korkmaz.constraint.username.notblank}")
        @Size(min = 4, max = 255)
        String username,

                @NotBlank
                @Email
                @UniqueEmail
                        String email,

                @Size(min = 8, max = 255)
                @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{salih_korkmaz.constraint.password.pattern}")
                        String password
) {
        public User toUser(){
                User user = new User();
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                return user;

        }
}
