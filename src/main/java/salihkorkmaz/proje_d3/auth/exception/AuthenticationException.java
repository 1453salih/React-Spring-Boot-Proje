package salihkorkmaz.proje_d3.auth.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import salihkorkmaz.proje_d3.shared.Messages;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super(Messages.getMessageForLocale("salihkorkmaz.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}