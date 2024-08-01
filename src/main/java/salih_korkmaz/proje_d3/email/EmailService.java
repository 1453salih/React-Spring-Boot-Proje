package salih_korkmaz.proje_d3.email;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    public EmailService() {
        this.initialize();
    }

    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("zora.heathcote20@ethereal.email"); //Username alanı
        mailSender.setPassword("3fcQmq7ffcK66uyZcT");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
    }

    public void sendActivationEmail(String email, String activationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@my-app.com");
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activation?token=" + activationToken);
        this.mailSender.send(message);
    }
}
