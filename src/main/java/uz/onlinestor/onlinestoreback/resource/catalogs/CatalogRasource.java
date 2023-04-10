package uz.onlinestor.onlinestoreback.resource.catalogs;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.onlinestor.onlinestoreback.dto.CatalogDto;
import uz.onlinestor.onlinestoreback.fileupload.FileService;
import uz.onlinestor.onlinestoreback.models.catalogs.Catalog;
import uz.onlinestor.onlinestoreback.service.catalogs.CatalogService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.parseMediaType;

@Log4j2
@RestController
@RequestMapping("/online/doc/catalog/")
@RequiredArgsConstructor
public class CatalogRasource {

    @Autowired
    private final CatalogService catalogService;
    private final FileService fileService;


    @GetMapping("get")
    private List<CatalogDto> getAll() {
        return catalogService.getAllCatalogDto();
    }

    @GetMapping("getall")
    private List<Catalog> getAllActiveAllOfThem() {
        return catalogService.getAllActiveAllOfThem();
    }

//    @GetMapping("getbyparentid")
//    private List<CatalogDto> getByParentId(@RequestParam("parent_id") String parent_id) {
//        return catalogService.getByParentId(Long.parseLong(parent_id));
//    }

    @PostMapping("save")
    private CatalogDto save(@RequestBody Catalog catalog) {

        return catalogService.save(catalog);
    }

    @PostMapping("savesub")
    private Catalog savesub(@RequestParam("id") String id, @RequestBody Catalog catalog) {
        return catalogService.saveSub(Long.parseLong(id), catalog);
    }

    @PutMapping("deleteactive")
    private Catalog deleteActive(@RequestParam("id") String id) {
        return catalogService.deleteActive(Long.parseLong(id));
    }

    @DeleteMapping("delete/{id}")
    private void delete(@PathVariable Long id) throws Exception {
        catalogService.delete(id);
    }

    @PostMapping(value = "upload")
    private ResponseEntity<?> uploadAndDownload(
            @RequestParam(value = "id") String id,
            @RequestParam("file") MultipartFile file) {

        Catalog catalog = catalogService.getById(Long.parseLong(id));
        String substr = fileService.getType(file);
        catalog.setImagepath(catalog.getId() + substr);
        catalogService.save(catalog);
        return ResponseEntity.ok(fileService.storeFile(file, catalog.getImagepath(), "catalogs"));
    }


    @GetMapping("download/{id:.+}")
    private ResponseEntity<?> downloadFile(@PathVariable("id") String id, HttpServletRequest request) throws IOException {

       Catalog catalog = catalogService.getById(Long.parseLong(id));
        Resource fileResource = fileService.getFile(catalog.getImagepath(), "catalogs");

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
    @PostMapping("deleteImage")
    private void deleteImage(@RequestParam("id") Long id) throws Exception {
        catalogService.deleteImage(id);
    }
}
