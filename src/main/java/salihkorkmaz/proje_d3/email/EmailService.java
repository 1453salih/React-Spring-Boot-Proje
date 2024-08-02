package salihkorkmaz.proje_d3.email;


import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import salihkorkmaz.proje_d3.configuration.SalihKorkmazProperties;


import java.util.Locale;
import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    SalihKorkmazProperties salihKorkmazProperties;

    @Autowired
    MessageSource messageSource;



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

    String activationEmail = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;


    public void sendActivationEmail(String email, String activationToken) {

        var activationUrl = salihKorkmazProperties.getClient().host() + "/activation/" + activationToken;
        var title = messageSource.getMessage("salihkorkmaz.mail.user.created.title", null, LocaleContextHolder.getLocale());
        var clickHere = messageSource.getMessage("salihkorkmaz.mail.user.created.click.here", null, LocaleContextHolder.getLocale());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");
        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);


        try {
            message.setFrom(salihKorkmazProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
    }
}
