package uz.onlinestor.onlinestoreback.service.catalogs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.dto.CatalogDto;
import uz.onlinestor.onlinestoreback.fileupload.FileService;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.catalogs.Catalog;
import uz.onlinestor.onlinestoreback.repository.catalogs.CatalogRepository;
import uz.onlinestor.onlinestoreback.repository.catalogs.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CatalogService {

    @Autowired
    final CatalogRepository catalogRepository;
    @Autowired
    final ProductRepository productRepository;
    private final FileService fileService;

    private CatalogDto convertToCatalogDto(Catalog catalog) {

            CatalogDto catalogDto = new CatalogDto();
            catalogDto.setId(catalog.getId());
            catalogDto.setCatalogname(catalog.getCatalogname());
//        catalogDto.setImagepath(catalog.getImagepath());
            catalogDto.setActive(catalog.getActive());
            catalogDto.setCatalogs(catalog.getCatalogs());
            catalogDto.setParent(catalog.getParent());

        return catalogDto;
    }

    public List<CatalogDto> getAllCatalogDto() {
        return catalogRepository.
                getAllActive(Status.ACTIVE).
                stream().
                map(this::convertToCatalogDto).
                collect(Collectors.toList());
    }

    public Catalog getById(Long id) {
        return catalogRepository.findById(id).orElse(null);
    }

    public List<Catalog> getAllActiveAllOfThem() {
        return catalogRepository.getAllActiveAllOfThem(Status.ACTIVE);
    }

    public CatalogDto save(Catalog catalog) {
        return convertToCatalogDto(catalogRepository.save(catalog));
    }

    public Catalog saveSub(Long id, Catalog catalog) {
        Optional<Catalog> oldCatalog = catalogRepository.findById(id);
        Catalog oldcatalog1;
        if (oldCatalog.isPresent()) {
            oldcatalog1 = oldCatalog.orElse(null);
            catalog.setActive(Status.ACTIVE);
            oldcatalog1.addCatalog(catalog);

            return catalogRepository.save(oldcatalog1);
        }
        return null;
    }

    public Catalog deleteActive(Long id) {
        Optional<Catalog> catalogOptional = catalogRepository.findById(id);
        if (catalogOptional.isPresent()) {
            Catalog catalog = catalogOptional.get();
            catalog.setActive(Status.NOACTIVE);
            return catalogRepository.save(catalog);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        catalogRepository.deleteById(id);
    }


    public void deleteImage(Long id) {
        Catalog catalog = catalogRepository.findById(id).orElse(null);
        fileService.delete("catalogs-" + catalog.getImagepath());
        catalog.setImagepath("");
        catalogRepository.save(catalog);
    }

}
