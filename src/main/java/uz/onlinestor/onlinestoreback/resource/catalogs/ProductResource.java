package uz.onlinestor.onlinestoreback.resource.catalogs;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.dto.ProductDto;
import uz.onlinestor.onlinestoreback.models.catalogs.Product;
import uz.onlinestor.onlinestoreback.service.catalogs.ProductService;

import java.util.List;

@RestController
@RequestMapping("/online/doc/product/")
@RequiredArgsConstructor
public class ProductResource {

    @Autowired
    private final ProductService productService;

    @GetMapping("v1/get")
    private List<ProductDto> getAll(@RequestParam("id") String catalog_id) {
        return productService.getAllProductDto(Long.parseLong(catalog_id));
    }

    @GetMapping("v1/getbyid")
    private Product getById(@RequestParam("id") String id) {
        return productService.getById(Long.parseLong(id));
    }


    @PutMapping("delete")
    private Product delete(@RequestParam("id") Long id) throws Exception {
        return productService.delete(id);
    }

    @PostMapping("save")
    private ProductDto saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("savemark")
    private ProductDto saveMark(@RequestParam("id") Long id,
                                @RequestParam("user_id") Long user_id,
                                @RequestParam("mark") int mark){
        return productService.saveMark(id, user_id, mark);
    }



}
