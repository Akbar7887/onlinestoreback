package uz.onlinestor.onlinestoreback.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.catalogs.Catalog;

import java.util.List;


public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    @Query("select c from Catalog c where c.active = :active " +
            "and c.parent is null")
    List<Catalog> getAllActive(@Param("active") Status active);

    @Query("select c from Catalog c where c.active = :active")
    List<Catalog> getAllActiveAllOfThem(@Param("active") Status active);

//    @Query("select c from Catalog c where c.active = :active and c.parent.id =:parent_id")
//    List<Catalog> getByParent(@Param("active") ACTIVE active, @Param("parent_id") Long parent_id);

}