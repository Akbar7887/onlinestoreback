package uz.onlinestor.onlinestoreback.models.catalogs;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String imagepath;


    private Boolean mainimg = false;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    public ProductImage() {
    }

    public ProductImage(Long id, String imagepath, Boolean mainimg, Product product) {
        this.id = id;
        this.imagepath = imagepath;
        this.mainimg = mainimg;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Boolean getMainimg() {
        return mainimg;
    }

    public void setMainimg(Boolean mainimg) {
        this.mainimg = mainimg;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}