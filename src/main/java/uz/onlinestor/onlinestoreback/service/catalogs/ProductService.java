package uz.onlinestor.onlinestoreback.service.catalogs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.dto.ProductDto;
import uz.onlinestor.onlinestoreback.models.Status;
import uz.onlinestor.onlinestoreback.models.catalogs.Product;
import uz.onlinestor.onlinestoreback.models.contragent.MarkUser;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
import uz.onlinestor.onlinestoreback.repository.catalogs.CatalogRepository;
import uz.onlinestor.onlinestoreback.repository.catalogs.CharacteristicRepository;
import uz.onlinestor.onlinestoreback.repository.catalogs.ProductRepository;
import uz.onlinestor.onlinestoreback.repository.contragent.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    @Autowired
    final ProductRepository productRepository;
//    @Autowired
//    final CharacteristicRepository characteristicRepository;
    @Autowired
    final CatalogRepository catalogRepository;
    @Autowired
    final UserRepository userRepository;

    public List<ProductDto> getAllProductDto(Long catalog_id) {
        if (catalog_id == -1) {
            return productRepository
                    .getAllActive(Status.ACTIVE)
                    .stream()
                    .map(this::convertToProductDto)
                    .collect(Collectors.toList());
        } else {
            return productRepository
                    .getAllActiveById(Status.ACTIVE, catalog_id)
                    .stream()
                    .map(this::convertToProductDto)
                    .collect(Collectors.toList());
        }

    }

    private ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();

        Stream<MarkUser> filtered = product.getMarkUsers().stream().filter(o -> o.getMark() > 0);
        double sum = filtered.mapToDouble(MarkUser::getMark).sum();
        int size = product.getMarkUsers().size();
        double mark = 0;
        if (sum != 0 && size != 0){
            mark = sum/size;
        }

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCatalog(product.getCatalog());
        productDto.setProductImages(product.getProductImages());
        productDto.setPrices(product.getPrices());
        productDto.setMarkuser(mark);
        productDto.setMarksize(size);
        productDto.setCodeproduct(product.getCodeproduct());
        return productDto;
    }

    public List<Product> getAllActive(Long catalog_id) {
        return productRepository.getAllActiveById(Status.ACTIVE, catalog_id);
    }

    public List<Product> getAllActive() {
        return productRepository.getAllActive(Status.ACTIVE);
    }

    public Product delete(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        product.setStatus(Status.NOACTIVE);
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public ProductDto save(Product product) {

        return convertToProductDto(productRepository.save(product));
    }

    public ProductDto saveMark(Long id, Long user_id, int mark) {
        Product product = productRepository.findById(id).orElse(null);
        UserApp userApp = userRepository.findById(user_id).orElse(null);

        MarkUser markUser = new MarkUser();
        markUser.setUserApp(userApp);
        markUser.setMark(mark);
        product.addMarks(markUser);
        return convertToProductDto(productRepository.save(product));
    }
}
