package py.com.poraplz.cursomc.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import py.com.poraplz.cursomc.entities.enums.Perfil;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class User implements UserDetails{
    private static final long serialVersionUID= 1L;
    private Long id;
    private String email;
    private String pass;
    private Collection<? extends GrantedAuthority> authorities;

    public User(){

    }

    public User(Long id, String email, String pass, Set<Perfil> authorities) {

        this.id = id;
        this.email = email;
        this.pass = pass;
        this.authorities = authorities.stream().map(value -> new SimpleGrantedAuthority(value.getDescription())).collect(Collectors.toList());
    }

    public Long getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Metodo que permite implementar logica de vencimiento de cuenta.
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Metodo que permite implementar logica de bloqueo de cuenta.
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Metodo que permite implementar logica de vencimiento de credenciales.
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
