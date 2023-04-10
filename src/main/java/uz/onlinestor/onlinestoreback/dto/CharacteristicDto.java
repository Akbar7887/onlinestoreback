package uz.onlinestor.onlinestoreback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDto {


    private Long id;
    private String name;
    private String valuename;
    private Long productId;
}
