package salihkorkmaz.proje_d3.user.exception;
import salihkorkmaz.proje_d3.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super(Messages.getMessageForLocale("salihkorkmaz.auth.authorization.message", LocaleContextHolder.getLocale()));
    }
}
