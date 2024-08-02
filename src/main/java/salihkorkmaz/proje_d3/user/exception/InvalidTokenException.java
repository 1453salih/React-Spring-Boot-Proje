package salihkorkmaz.proje_d3.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import salihkorkmaz.proje_d3.shared.Messages;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super(Messages.getMessageForLocale("salihkorkmaz.active.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
