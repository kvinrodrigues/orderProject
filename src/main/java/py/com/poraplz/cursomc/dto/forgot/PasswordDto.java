package py.com.poraplz.cursomc.dto.forgot;

import javax.validation.constraints.NotEmpty;

public class PasswordDto {
    @NotEmpty(message = "Campo Obligatorio")
    private String newPassword;
    private String token;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
