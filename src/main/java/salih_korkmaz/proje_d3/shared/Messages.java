package salih_korkmaz.proje_d3.shared;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    public static String getMessageForLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle("messages", locale).getString(messageKey);
    }

}