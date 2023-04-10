package uz.onlinestor.onlinestoreback.models.calculate;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

enum RATES  { USD, EUR, UZS };

@Entity
@Table(name = "exchange_rates")
public class ExchangeRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Enumerated(EnumType.STRING)
    private RATES rates = RATES.USD;

    private double ratevalue;


    public ExchangeRates() {
    }

    public ExchangeRates(Long id, Date date, RATES rates, double ratevalue) {
        this.id = id;
        this.date = date;
        this.rates = rates;
        this.ratevalue = ratevalue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RATES getRates() {
        return rates;
    }

    public void setRates(RATES rates) {
        this.rates = rates;
    }

    public double getRatevalue() {
        return ratevalue;
    }

    public void setRatevalue(double ratevalue) {
        this.ratevalue = ratevalue;
    }


}