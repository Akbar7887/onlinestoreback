package uz.onlinestor.onlinestoreback.models.contragent;


import uz.onlinestor.onlinestoreback.models.catalogs.Product;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "markuser")
public class MarkUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double mark = 1;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApp userApp;

    public MarkUser() {
    }

    public MarkUser(Long id, int mark, Product product, UserApp userApp) {
        this.id = id;
        this.mark = mark;
        this.product = product;
        this.userApp = userApp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }
}