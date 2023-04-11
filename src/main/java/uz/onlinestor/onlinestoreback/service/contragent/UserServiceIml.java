package uz.onlinestor.onlinestoreback.service.contragent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.onlinestor.onlinestoreback.models.contragent.ERole;
import uz.onlinestor.onlinestoreback.models.contragent.Role;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
import uz.onlinestor.onlinestoreback.repository.contragent.RoleRepository;
import uz.onlinestor.onlinestoreback.repository.contragent.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceIml implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User not found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public UserApp saveUser(UserApp user) {
        log.info("Saving new password to the datebase");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role to the datebase", role.getName());
        return roleRepository.save(role);
    }



    @Override
    public void addRoleToUser(String username, ERole rolename) {

        log.info("Saving new user and role the datebase", username, rolename);
        UserApp user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename).orElse(null);
        user.getRoles().add(role);
    }

    @Override
    public UserApp getUser(String username) {
        log.info("Fetching user{}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserApp> getUser() {
        log.info("Fetching all user");
        return userRepository.findAll();
    }

    @Override
    public List<UserApp> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
