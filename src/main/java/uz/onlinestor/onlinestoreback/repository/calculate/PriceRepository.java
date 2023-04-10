package uz.onlinestor.onlinestoreback.repository.calculate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.onlinestor.onlinestoreback.models.calculate.Price;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p where p.product.id = :id")
    public List<Price> getAll(@Param("id") Long id);
}