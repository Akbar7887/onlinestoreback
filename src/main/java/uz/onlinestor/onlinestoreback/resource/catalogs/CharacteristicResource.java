package uz.onlinestor.onlinestoreback.resource.catalogs;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.dto.CharacteristicDto;
import uz.onlinestor.onlinestoreback.models.catalogs.Characteristic;
import uz.onlinestor.onlinestoreback.service.catalogs.CharacteristicService;
import uz.onlinestor.onlinestoreback.service.catalogs.ProductService;

import java.util.List;

@RestController
@RequestMapping("/online/doc/characteristic/")
@RequiredArgsConstructor
public class CharacteristicResource {

    @Autowired
    private final CharacteristicService characteristicService;
    @Autowired
    private final ProductService productService;

    @GetMapping("get")
    private List<CharacteristicDto> getByProductId(@RequestParam("id") String id) {
        return characteristicService.getAllDto(Long.parseLong(id));
    }


//    @PostMapping("addcharacter")
//    private Product saveCharacter(@RequestParam("id") String id, @RequestBody Characteristic characteristic) {
//        return characteristicService.saveCharacteristic(Long.parseLong(id), characteristic);
//    }

    @PostMapping("save")
    private Characteristic saveCharacter(@RequestBody Characteristic characteristics) {
        return characteristicService.save(characteristics);
    }

    @DeleteMapping("removecharacter")
    private void saveCharacter(@RequestParam("id") String id) {
        characteristicService.removeCharacteristic(Long.parseLong(id));
    }
}
