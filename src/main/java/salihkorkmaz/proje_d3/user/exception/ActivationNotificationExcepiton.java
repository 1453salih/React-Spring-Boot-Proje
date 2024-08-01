package salihkorkmaz.proje_d3.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import salihkorkmaz.proje_d3.shared.Messages;

public class ActivationNotificationExcepiton extends RuntimeException {
    public ActivationNotificationExcepiton() {
        super(Messages.getMessageForLocale("salihkorkmaz.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
