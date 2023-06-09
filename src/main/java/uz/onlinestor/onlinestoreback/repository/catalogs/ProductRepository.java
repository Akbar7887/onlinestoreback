package uz.onlinestor.onlinestoreback.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.catalogs.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select c from Product c where c.status = :active and c.catalog.id = :catalog_id")
    List<Product> getAllActiveById(@Param("active") Status active, @Param("catalog_id") Long id);

    @Query("select c from Product c where c.status = :active")
    List<Product> getAllActive(@Param("active") Status active);
}