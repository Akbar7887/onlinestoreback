package uz.onlinestor.onlinestoreback.service.catalogs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.fileupload.FileService;
import uz.onlinestor.onlinestoreback.models.catalogs.ProductImage;
import uz.onlinestor.onlinestoreback.repository.catalogs.ProductImageRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductImageService {

    @Autowired
    final ProductImageRepository productImageRepository;
    private final FileService fileService;

    public List<ProductImage> getByParentId(Long id) {
        return productImageRepository.getByParentId(id);
    }

    public ProductImage getById(Long id) {
        return productImageRepository.getById(id);
    }

    public void delete(Long id) {
        ProductImage productImage = productImageRepository.getById(id);
        if (fileService.delete("products-" + productImage.getImagepath())){
            productImageRepository.deleteById(id);
        };
    }

    public ProductImage save(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

}
