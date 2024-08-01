package salihkorkmaz.proje_d3.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import salihkorkmaz.proje_d3.shared.Messages;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends  RuntimeException{
    public NotUniqueEmailException(){
        super(Messages.getMessageForLocale("salihkorkmaz.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("email",Messages.getMessageForLocale("salihkorkmaz.constraint.email.notunique",LocaleContextHolder.getLocale()));
    }
}
