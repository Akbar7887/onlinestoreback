package uz.onlinestor.onlinestoreback.service.contragent;

import uz.onlinestor.onlinestoreback.models.contragent.ERole;
import uz.onlinestor.onlinestoreback.models.contragent.Role;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

import java.util.List;

public interface UserService {

    UserApp saveUser(UserApp user);

    Role saveRole(Role role);

    void addRoleToUser(String username, ERole rolename);

    UserApp getUser(String username);

    List<UserApp> getUser();


    List<UserApp> findAll();

    void deleteById(Long id);
}
