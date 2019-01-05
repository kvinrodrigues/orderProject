package py.com.poraplz.cursomc.dto.client;


import org.hibernate.validator.constraints.Length;
import py.com.poraplz.cursomc.services.validation.ClientInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@ClientInsert
public class ClientNewDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "Campo obligatorio")
    @Length(min=5, max = 120, message = "Longitud de campo invalido")
    private String name;
    @NotEmpty(message = "Campo obligatorio")
    @Email(message = "Formato email invalido")
    private String email;
    @NotEmpty(message = "Campo obligatorio")
    private String cpfOuCnpj;
    private Integer type;
    @NotEmpty(message = "Campo obligatorio")
    private String street;
    private Integer number;
    private String complement;
    private String district;
    @NotEmpty(message = "Campo obligatorio")
    private String cep;
    @NotEmpty(message = "Campo obligatorio")
    private String firstPhone;
    private String secondPhone;
    private String thirdPhone;

    private Long cityId;
    @NotEmpty
    private String pass;

    public ClientNewDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getThirdPhone() {
        return thirdPhone;
    }

    public void setThirdPhone(String thirdPhone) {
        this.thirdPhone = thirdPhone;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
