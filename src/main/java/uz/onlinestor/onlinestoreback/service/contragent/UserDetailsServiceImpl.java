package uz.onlinestor.onlinestoreback.service.contragent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
import uz.onlinestor.onlinestoreback.repository.contragent.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userRepository.findByUsername(username);
        return UserDetailsImpl.build(user);
    }
}
