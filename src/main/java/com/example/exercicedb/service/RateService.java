package com.example.exercicedb.service;


import com.example.exercicedb.domain.data.RateData;
import com.example.exercicedb.domain.data.RateFilterData;

public interface RateService {

    RateData createRate(RateData rateData);

    RateData getRateById(Integer id);

    void deleteRate(Integer id);

    RateData getRateByFilter(RateFilterData build);

    void updatePrice(Integer id, Integer price);
}
