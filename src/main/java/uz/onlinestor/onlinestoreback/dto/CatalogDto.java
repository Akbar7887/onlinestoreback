package uz.onlinestor.onlinestoreback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.onlinestor.onlinestoreback.models.ACTIVE;
import uz.onlinestor.onlinestoreback.models.catalogs.Catalog;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDto {

    private Long id;
    private String catalogname;
    private String imagepath;
    private ACTIVE active;
    private List<Catalog> catalogs;
    private Catalog parent;


}
