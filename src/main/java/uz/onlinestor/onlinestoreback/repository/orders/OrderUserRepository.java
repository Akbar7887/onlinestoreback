package uz.onlinestor.onlinestoreback.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.onlinestor.onlinestoreback.models.orders.OrderUser;

import java.util.List;

public interface OrderUserRepository extends JpaRepository<OrderUser, Long> {

    @Query("select o from OrderUser o where o.userApp.id = :id and o.sold = false")
    List<OrderUser> getAllByUserId(@Param("id") Long id);

    @Query("select o from OrderUser o where o.product.id = ?1 and o.userApp.id = ?2 and o.sold = false")
    List<OrderUser> getByUserAndProductID(Long product_id,
                                          Long user_id);

  /*  @Query("select o from OrderUser o")
    List<OrderUser> getByUserAndProductID(Long product_id,
                                          Long user_id);
*/
}