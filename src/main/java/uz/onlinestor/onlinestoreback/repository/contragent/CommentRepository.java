package uz.onlinestor.onlinestoreback.repository.contragent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.onlinestor.onlinestoreback.models.contragent.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.product.id=:id")
    List<Comment> getAllbyProduct(@Param("id") Long id);
}