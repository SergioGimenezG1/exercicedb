package com.example.exercicedb.service;

import com.example.exercicedb.middleware.data.CurrencyDto;

public interface CurrencyService {

    CurrencyDto getCurrencyByCode(String currencyCode);
}
