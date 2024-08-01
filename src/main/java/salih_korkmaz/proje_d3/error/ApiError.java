package salih_korkmaz.proje_d3.error;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@JsonInclude(value = Include.NON_NULL)//spring errorları json çevirirken nullarıda çeviriyordu boş (ValidationsError:{}) jsonu kaldırmak için eğer boşsa gösterme dedik.
public class ApiError {

    private int status;

    private String message;

    private String path;

    private long timestamp = new Date().getTime();

    private Map<String,String> validationErrors = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
