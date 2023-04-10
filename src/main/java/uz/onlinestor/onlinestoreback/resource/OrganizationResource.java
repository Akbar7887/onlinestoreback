package uz.onlinestor.onlinestoreback.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.models.Organization;
import uz.onlinestor.onlinestoreback.service.OrganizationService;

@RestController
@RequestMapping("/online/organization/")
@RequiredArgsConstructor
public class OrganizationResource {

    private final OrganizationService organizationService;

    @GetMapping("get")
    private Organization getAll() {
        return organizationService.getFirst().orElse(null);
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
