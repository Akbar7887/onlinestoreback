package uz.onlinestor.onlinestoreback.models.catalogs;

import javax.persistence.*;

@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value_name", nullable = false)

    private String valuename;

    @ManyToOne()
    @JoinColumn(name = "product_id",
            referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_id_fk"))
    private Product product;


    public Characteristic() {
    }

    public Characteristic(Long id, String name, String valuename, Product product) {
        this.id = id;
        this.name = name;
        this.valuename = valuename;
        this.product = product;
    }

    public Long getProductId() {
        if (this.product != null) {
            return this.product.getId();
        } else {
            return null;
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

    public String getValuename() {
        return valuename;
    }

    public void setValuename(String valuename) {
        this.valuename = valuename;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}