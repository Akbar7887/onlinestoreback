package uz.onlinestor.onlinestoreback.service.catalogs;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.dto.CharacteristicDto;
import uz.onlinestor.onlinestoreback.models.catalogs.Characteristic;
import uz.onlinestor.onlinestoreback.repository.catalogs.CharacteristicRepository;
import uz.onlinestor.onlinestoreback.repository.catalogs.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CharacteristicService {

    @Autowired
    final CharacteristicRepository characteristicRepository;
    @Autowired
    final ProductRepository productRepository;
    // == == == Characteristic


    public List<CharacteristicDto> getAllDto(Long id) {
        return characteristicRepository
                .getByProductId(id)
                .stream()
                .map(this::convertToCharacterDto)
                .collect(Collectors.toList());
    }

    private CharacteristicDto convertToCharacterDto(Characteristic characteristic) {
        CharacteristicDto characteristicDto = new CharacteristicDto();
        characteristicDto.setId(characteristic.getId());
        characteristicDto.setName(characteristic.getName());
        characteristicDto.setValuename(characteristic.getValuename());
        characteristicDto.setProductId(characteristic.getProductId());
        return characteristicDto;
    }


//    public Product saveCharacteristic(Long id, Characteristic characteristic) {
//        Optional<Product> productOptional = productRepository.findById(id);
//        if (productOptional.isPresent()) {
//            Product product = productOptional.get();
//            if (characteristic.getId() == null) {
//                product.addCharacteristic(characteristic);
//                return productRepository.save(product);
//            } else {
//                characteristic.setProduct(product);
//                characteristicRepository.save(characteristic);
//                return product;
//            }
//        } else {
//            return null;
//        }
//
//    }

    public Characteristic save(Characteristic characteristics) {
                  return characteristicRepository.save(characteristics);
    }

    public void removeCharacteristic(Long id) {
        Optional<Characteristic> characteristicOptional = characteristicRepository.findById(id);
        if (characteristicOptional.isPresent()) {
            Characteristic characteristic = characteristicOptional.get();
            characteristicRepository.delete(characteristic);
        }
    }
}
