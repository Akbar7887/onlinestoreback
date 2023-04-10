package uz.onlinestor.onlinestoreback.resource.catalogs;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.onlinestor.onlinestoreback.fileupload.FileService;
import uz.onlinestor.onlinestoreback.models.catalogs.Product;
import uz.onlinestor.onlinestoreback.models.catalogs.ProductImage;
import uz.onlinestor.onlinestoreback.service.catalogs.ProductImageService;
import uz.onlinestor.onlinestoreback.service.catalogs.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.parseMediaType;

@Log4j2
@RestController
@RequestMapping("/online/doc/productimage/")
@RequiredArgsConstructor
public class ProductImageResource {

    @Autowired
    private final  ProductImageService productImageService;
    @Autowired
    private final ProductService productService;
    private final FileService fileService;

    @GetMapping("get")
    private List<ProductImage> getByParentId(@RequestParam("id") String id){
        return productImageService.getByParentId(Long.parseLong(id));
    }

    @PostMapping("save")
    private ProductImage save(@RequestBody ProductImage productImage){
        return  productImageService.save(productImage);
    }

    @PostMapping(value = "upload")
    private ResponseEntity<ProductImage> uploadAndDownload(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "parent_id") String parent_id,
            @RequestParam(value = "mainimg") boolean mainimg,
            @RequestParam("file") MultipartFile file) {
        ProductImage productImagenew;

        Product product = productService.getById(Long.parseLong(parent_id));
        if(product == null){
            return null;
        }
        if(Objects.equals(id, "")){
            productImagenew = new ProductImage();
            productImagenew.setProduct(product);
            productImageService.save(productImagenew);
        }else {
            productImagenew = productImageService.getById(Long.parseLong(id));
        }

        productImagenew.setProduct(product);
        String substr = fileService.getType(file);
        productImagenew.setImagepath(productImagenew.getId() + substr);

        productImagenew.setMainimg(mainimg);
        productImagenew.setProduct(product);
        productImageService.save(productImagenew);
        fileService.storeFile(file, productImagenew.getImagepath(), "products");
        return ResponseEntity.ok(productImagenew);
    }


    @GetMapping("download/{id:.+}")
    private ResponseEntity<?> downloadFile(@PathVariable("id") String id, HttpServletRequest request) throws IOException {

        ProductImage productImage = productImageService.getById(Long.parseLong(id));
        Resource fileResource = fileService.getFile(productImage.getImagepath(), "products");

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(fileResource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);

    }

    @DeleteMapping("deleteimage")
    private void delete(@RequestParam("id") Long id)  {
        productImageService.delete(id);
    }

}
