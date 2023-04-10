package uz.onlinestor.onlinestoreback.models.contragent;


import uz.onlinestor.onlinestoreback.models.catalogs.Product;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private UserApp userApp;

    @ManyToOne()
    @JoinColumn(name = "product_id",
            referencedColumnName = "id")
    private Product product;

    public Comment() {
    }

    public Comment(Long id, String comment, UserApp userApp, Product product) {
        this.id = id;
        this.comment = comment;
        this.userApp = userApp;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}