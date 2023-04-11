package uz.onlinestor.onlinestoreback.models.catalogs;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NonNull;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.calculate.Price;
import uz.onlinestor.onlinestoreback.models.contragent.Comment;
import uz.onlinestor.onlinestoreback.models.contragent.MarkUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NonNull
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "codeproduct")
    private String codeproduct;

    @Enumerated(value = EnumType.STRING)
    private Status active = Status.ACTIVE;


    @ManyToOne()
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private Catalog catalog;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductImage> productImages = new ArrayList<>();

//    @OneToMany(mappedBy = "product")
//    @JsonManagedReference
//    private List<Characteristic> characteristics = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            targetEntity = Price.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Price> prices = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<MarkUser> markUsers = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    public Product() {
    }

    public Product(Long id, String codeproduct, @NonNull String name, String description, Status active, Catalog catalog, List<ProductImage> productImages, List<Price> prices, List<MarkUser> markUsers, List<Comment> comments) {
        this.id = id;
        this.codeproduct = codeproduct;
        this.name = name;
        this.description = description;

        this.active = active;
        this.catalog = catalog;
        this.productImages = productImages;
        this.prices = prices;
        this.markUsers = markUsers;
        this.comments = comments;
    }

    public void addPrice(Price price) {
        if (!this.prices.contains(price)) {
            this.prices.add(price);
            price.setProduct(this);
        }
    }

    public void removePrice(Price price) {
        if (this.prices.contains(price)) {
            this.prices.remove(price);
            price.setProduct(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getActive() {
        return active;
    }

    public void setActive(Status active) {
        this.active = active;
    }

    public Catalog getCatalog() {

        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }


    public Long getCatalogId() {

        if (this.catalog != null) {
            return this.catalog.getId();
        } else {
            return null;
        }
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<MarkUser> getMarkUsers() {
        return markUsers;
    }

    public void setMarkUsers(List<MarkUser> markUsers) {
        this.markUsers = markUsers;
    }

    public void addMarks(MarkUser markUser){
        if(!this.markUsers.contains(markUser)){
            this.markUsers.add(markUser);
            markUser.setProduct(this);
        }
    }

    public String getCodeproduct() {
        return codeproduct;
    }

    public void setCodeproduct(String codeproduct) {
        this.codeproduct = codeproduct;
    }
}

