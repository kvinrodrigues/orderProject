package py.com.poraplz.cursomc.dto.forgot;


import javax.validation.constraints.Email;
import java.util.Objects;

public class ForgotPassDTO{

    public ForgotPassDTO() {
    }

    public ForgotPassDTO(String email) {
        this.email = email;
    }


    @Email(message = "Email invalido")
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForgotPassDTO that = (ForgotPassDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ForgotPassDTO{");
        sb.append("email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

