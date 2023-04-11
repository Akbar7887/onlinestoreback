package uz.onlinestor.onlinestoreback.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.catalogs.Characteristic;

import java.util.List;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    @Query("select c from Characteristic c where c.product.id= :id")
    List<Characteristic> getByProductId(@Param("id") Long id);
}