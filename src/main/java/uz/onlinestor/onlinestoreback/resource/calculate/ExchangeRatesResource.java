package uz.onlinestor.onlinestoreback.resource.calculate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.models.calculate.ExchangeRates;
import uz.onlinestor.onlinestoreback.service.calculate.ExchangeRatesService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/online/doc/exchange/")
@RequiredArgsConstructor
public class ExchangeRatesResource {

    @Autowired
    final private ExchangeRatesService exchangeRatesService;

    @GetMapping("get")
    private List<ExchangeRates> getAll() {
        return exchangeRatesService.getAll();
    }

    @GetMapping("getbydate")
    private ExchangeRates getbyDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        return exchangeRatesService.getbyDate(date);
    }

    @PostMapping("save")
    private ExchangeRates save(@RequestBody ExchangeRates exchangeRates) {
        return exchangeRatesService.save(exchangeRates);
    }

    @DeleteMapping("delete")
    private void delete(@RequestParam("id") String id) {
        exchangeRatesService.delete(Long.parseLong(id));
    }
}
