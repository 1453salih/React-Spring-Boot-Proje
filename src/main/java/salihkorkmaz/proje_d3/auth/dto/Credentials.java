package salihkorkmaz.proje_d3.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Credentials(@Email String email,@NotBlank String password) {

}
