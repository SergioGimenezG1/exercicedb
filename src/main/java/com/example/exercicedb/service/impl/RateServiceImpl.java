package com.example.exercicedb.service.impl;


import com.example.exercicedb.adapter.entity.RateRepositoryAdapter;
import com.example.exercicedb.adapter.middleware.CurrencyMiddlewareAdapter;
import com.example.exercicedb.domain.data.RateData;
import com.example.exercicedb.domain.data.RateFilterData;
import com.example.exercicedb.repository.RateRepository;
import com.example.exercicedb.service.CurrencyService;
import com.example.exercicedb.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private RateRepositoryAdapter repositoryAdapter;
    @Autowired
    private CurrencyMiddlewareAdapter currencyMiddlewareAdapter;
    @Autowired
    private CurrencyService currencyService;

    @Override
    public RateData createRate(RateData rateData) {
        log.info("Init for createRate {}", rateData);
        RateData res = repositoryAdapter.fromTargetToSource(rateRepository.save(repositoryAdapter.fromSourceToTarget(rateData)));
        res.setCurrency(currencyMiddlewareAdapter.fromSourceToTarget(currencyService.getCurrencyByCode(res.getCurrency().getCode())));
        return res;
    }

    @Override
    public RateData getRateById(Integer id) {
        log.info("Init for getRateById {}", id);
        return rateRepository.findById(id).map(rateEntity -> repositoryAdapter.fromTargetToSource(rateEntity)).orElseThrow(
                () -> new RuntimeException(String.format("Unable to find any rate with the id %s", id)));
    }

    @Override
    public void deleteRate(Integer id) {
        log.info("Init for deleteRate {}", id);
        rateRepository.deleteById(id);
    }

    @Override
    public RateData getRateByFilter(RateFilterData rateFilterData) {
        return repositoryAdapter.fromTargetToSource(rateRepository.getRateByFilter(rateFilterData.getDate(), rateFilterData.getProductId(), rateFilterData.getBrandId()));
    }

    @Override
    public void updatePrice(Integer id, Integer price) {
        rateRepository.updatePrice(id, price);
    }
}
