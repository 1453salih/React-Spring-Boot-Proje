package salihkorkmaz.proje_d3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import salihkorkmaz.proje_d3.user.User;
import salihkorkmaz.proje_d3.user.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjeD3Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjeD3Application.class, args);
    }


    //? 25 Adet kullanıcı ekleme işlemi
    @Bean
    @Profile("dev")
    CommandLineRunner userCreator(UserRepository userRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return (args) -> {
            for (var i = 1; i <= 25; i++) {
                User user = new User();
                user.setUsername("user" + i);
                user.setEmail("user" + i + "@gmail.com");
                user.setPassword(passwordEncoder.encode("P4ssword"));
                user.setActive(true);
                userRepository.save(user);
            }
        };
    }

}
