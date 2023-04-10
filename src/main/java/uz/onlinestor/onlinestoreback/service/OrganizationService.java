package uz.onlinestor.onlinestoreback.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.models.Organization;
import uz.onlinestor.onlinestoreback.repository.OrganizationRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrganizationService{

    private final OrganizationRepository organizationRepository;


    public Optional<Organization> getFirst() {
        return organizationRepository.findAll().stream().findFirst();
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public void delete(Long id) {
        organizationRepository.deleteById(id);
    }
}
