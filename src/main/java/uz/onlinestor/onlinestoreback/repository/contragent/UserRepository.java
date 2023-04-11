package uz.onlinestor.onlinestoreback.repository.contragent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

import java.util.List;

public interface UserRepository extends JpaRepository<UserApp, Long> {


    @Query("select u from UserApp u where u.active = :active")
    List<UserApp> getAll(@Param("active") Status active);

    UserApp findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByPhone(String phone);

}