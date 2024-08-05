package salihkorkmaz.proje_d3.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import salihkorkmaz.proje_d3.shared.Messages;

public class NotFoundException extends RuntimeException{


    public NotFoundException(long id) {
        super(Messages.getMessageForLocale("salihkorkmaz.user.not.found", LocaleContextHolder.getLocale(),id));
    }
}
