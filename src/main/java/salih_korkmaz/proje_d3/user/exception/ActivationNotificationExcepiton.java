package salih_korkmaz.proje_d3.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import salih_korkmaz.proje_d3.shared.Messages;

public class ActivationNotificationExcepiton extends RuntimeException {
    public ActivationNotificationExcepiton() {
        super(Messages.getMessageForLocale("salih_korkmaz.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
