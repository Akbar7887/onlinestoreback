package uz.onlinestor.onlinestoreback.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.models.Organization;
import uz.onlinestor.onlinestoreback.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/online/organization/")
@RequiredArgsConstructor
public class OrganizationResource {

    private final OrganizationService organizationService;

    @GetMapping("v1/get")
    private List<Organization> getAll() {
        return organizationService.getAll();
    }

    @PostMapping("save")
    private ResponseEntity<Organization> save(@RequestBody Organization organization) {
        return ResponseEntity.ok().body(organizationService.save(organization));
    }

    @DeleteMapping("delete/{id}")
    private void delete(@PathVariable Long id) throws Exception {

        organizationService.delete(id);
    }
}
