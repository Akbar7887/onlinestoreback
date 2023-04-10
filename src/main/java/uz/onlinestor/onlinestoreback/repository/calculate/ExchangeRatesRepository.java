package uz.onlinestor.onlinestoreback.repository.calculate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.onlinestor.onlinestoreback.models.calculate.ExchangeRates;import uz.onlinestor.onlinestoreback.models.calculate.ExchangeRates;

import java.util.Date;


public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Long> {

    @Query(value = "SELECT * FROM exchange_rates  WHERE date <= ?1 order by date desc LIMIT 1", nativeQuery = true)
    ExchangeRates getByDate(@Param("date") Date date);
}