package uz.onlinestor.onlinestoreback.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.catalogs.ProductImage;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query("select primg from ProductImage primg where primg.product.id = :id")
    List<ProductImage> getByParentId(@Param("id") Long id);

    @Query("select primg from ProductImage primg where primg.id = :id")
    ProductImage getById(@Param("id") Long id);

}