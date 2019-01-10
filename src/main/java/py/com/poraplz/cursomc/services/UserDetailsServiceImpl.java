package py.com.poraplz.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import py.com.poraplz.cursomc.entities.Cliente;
import py.com.poraplz.cursomc.security.JWTUtil;
import py.com.poraplz.cursomc.security.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteService clientService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente client = clientService.getClientByEmail(email);
        if(client == null){
            throw new UsernameNotFoundException(email);
        }
        return new User(jwtUtil, client.getId(), client.getEmail(), client.getPassword(), client.getProfiles(), client.getForgotPassToken());
    }
}
