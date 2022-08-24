package com.user.validate.user.controller;

import com.user.validate.user.exception.CurrencyCodeNotFoundException;
import com.user.validate.user.model.Currency;
import com.user.validate.user.model.MessageCode;
import com.user.validate.user.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/currencyDetails/{currencyCode}")
    public ResponseEntity<Currency> currency(@PathVariable("currencyCode") String currencyCode) throws CurrencyCodeNotFoundException {

        Currency currency = currencyService.getCurrencyDetails(currencyCode);
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/currencyDetails/")
    public ResponseEntity<List<Currency>> allCurrency() {

        List<Currency> messageCodes = currencyService.getAllCurrencyDetails();
        return new ResponseEntity<>(messageCodes, HttpStatus.OK);

    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping("conversionRate/{currencyCode}")
    public ResponseEntity<Float> getConversionRate(@PathVariable("currencyCode") String currencyCode) throws CurrencyCodeNotFoundException {

        float conversionRate = currencyService.getConversionRate(currencyCode);
        return new ResponseEntity<>(conversionRate, HttpStatus.OK);

    }

}
