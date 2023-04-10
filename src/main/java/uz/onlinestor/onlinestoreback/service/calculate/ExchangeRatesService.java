package uz.onlinestor.onlinestoreback.service.calculate;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.models.calculate.ExchangeRates;
import uz.onlinestor.onlinestoreback.repository.calculate.ExchangeRatesRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExchangeRatesService {

    @Autowired
    final private ExchangeRatesRepository exchangeRatesRepository;

    public List<ExchangeRates> getAll(){
        return  exchangeRatesRepository.findAll();
    }

    public ExchangeRates getbyDate(Date date){
        return exchangeRatesRepository.getByDate(date);
    }

    public ExchangeRates save(ExchangeRates exchangeRates){
        return exchangeRatesRepository.save(exchangeRates);
    }

    public void delete(Long id){
        exchangeRatesRepository.deleteById(id);
    }

}
