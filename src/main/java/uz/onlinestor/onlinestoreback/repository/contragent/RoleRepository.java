package uz.onlinestor.onlinestoreback.repository.contragent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.contragent.ERole;
import uz.onlinestor.onlinestoreback.models.contragent.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole eRole);
}
