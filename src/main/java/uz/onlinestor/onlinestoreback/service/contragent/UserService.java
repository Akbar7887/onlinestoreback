package uz.onlinestor.onlinestoreback.service.contragent;


import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

import java.util.List;

public interface UserService {

    UserApp register(UserApp user);

    List<UserApp> getAll();

    UserApp findByUsername(String username);

    UserApp findById(Long id);

    void delete(Long id);
}