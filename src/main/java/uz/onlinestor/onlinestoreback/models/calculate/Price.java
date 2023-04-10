package uz.onlinestor.onlinestoreback.models.calculate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import uz.onlinestor.onlinestoreback.models.catalogs.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private Date date;

    @Enumerated(EnumType.STRING)
    private RATES rates = RATES.USD;

    private double price;

    @Column(nullable = false)
    private double pricesum;

    @ManyToOne()
    @JoinColumn(name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_product"),
            nullable = false)
    @JsonBackReference
    private Product product;


    public Price() {
    }

    public Price(Long id, Date date, RATES rates, double price, double pricesum, Product product) {
        this.id = id;
        this.date = date;
        this.rates = rates;
        this.price = price;
        this.pricesum = pricesum;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RATES getRates() {
        return rates;
    }

    public void setRates(RATES rates) {
        this.rates = rates;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPricesum() {
        return pricesum;
    }

    public void setPricesum(double pricesum) {
        this.pricesum = pricesum;
    }


}