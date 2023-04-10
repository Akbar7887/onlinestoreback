package uz.onlinestor.onlinestoreback.models.orders;


import org.springframework.data.annotation.CreatedDate;
import uz.onlinestor.onlinestoreback.models.catalogs.Product;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_user")
public class OrderUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id",
            referencedColumnName = "id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "userapp_id",
            referencedColumnName = "id")
    private UserApp userApp;

    private int quantity;

    private double price;

    private boolean sold = false;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecraete = new Date();

    public OrderUser() {
    }

    public OrderUser(Long id, Product product, UserApp userApp, int quantity, double price, boolean sold, Date datecraete) {
        this.id = id;
        this.product = product;
        this.userApp = userApp;
        this.quantity = quantity;
        this.price = price;
        this.sold = sold;
        this.datecraete = datecraete;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDatecraete() {
        return datecraete;
    }

    public void setDatecraete(Date datecraete) {
        this.datecraete = datecraete;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }


}