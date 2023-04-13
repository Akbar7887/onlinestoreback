package uz.onlinestor.onlinestoreback.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.calculate.Price;
import uz.onlinestor.onlinestoreback.models.catalogs.Catalog;
import uz.onlinestor.onlinestoreback.models.catalogs.Characteristic;
import uz.onlinestor.onlinestoreback.models.catalogs.ProductImage;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private Long id;
    private String name;
    private String description;
    private Status status = Status.ACTIVE;
    private Catalog catalog;
    private String codeproduct;
    private List<ProductImage> productImages;
    private List<Price> prices;
    private double markuser;
    private int marksize;

}
