package uz.onlinestor.onlinestoreback.service.calculate;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.models.calculate.Price;
import uz.onlinestor.onlinestoreback.repository.calculate.PriceRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getAll(Long id){
        return  priceRepository.getAll(id);
    }

    public Price save(Price price){
        return priceRepository.save(price);
    }

    public void delete(Long id){
        priceRepository.deleteById(id);
    }
}
