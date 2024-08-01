package salihkorkmaz.proje_d3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ProjeD3Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjeD3Application.class, args);
    }

}
