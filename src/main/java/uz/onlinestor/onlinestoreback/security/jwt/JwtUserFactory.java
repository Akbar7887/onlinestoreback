package uz.onlinestor.onlinestoreback.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.contragent.Role;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserApp user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().name())
                ).collect(Collectors.toList());
    }
}
