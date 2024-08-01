package salihkorkmaz.proje_d3.email;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import salihkorkmaz.proje_d3.configuration.SalihKorkmazProperties;


import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    SalihKorkmazProperties salihKorkmazProperties;


    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(salihKorkmazProperties.getEmail().host());
        mailSender.setPort(salihKorkmazProperties.getEmail().port());
        mailSender.setUsername(salihKorkmazProperties.getEmail().username()); //Username alanı
        mailSender.setPassword(salihKorkmazProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
    }

    public void sendActivationEmail(String email, String activationToken) {

        var activationUrl = salihKorkmazProperties.getClient().host() + "/activation/" + activationToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(salihKorkmazProperties.getEmail().from());
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText(activationUrl);
        this.mailSender.send(message);
    }
}
