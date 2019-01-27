package py.com.poraplz.cursomc.dto.forgot;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo obligatorio") @Email(message = "Emal invalido")
    public String email;

    public EmailDTO() {
    }

    public EmailDTO(@Email String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
