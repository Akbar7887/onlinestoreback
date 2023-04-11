package uz.onlinestor.onlinestoreback.repository.contragent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {


    @Query("select u from UserApp u where u.active = :active")
    List<UserApp> getAll(@Param("active") Status active);

    UserApp findByUsername(String username);

}