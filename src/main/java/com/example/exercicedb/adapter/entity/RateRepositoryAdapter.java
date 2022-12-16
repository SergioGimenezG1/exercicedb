package com.example.exercicedb.adapter.entity;

import com.example.exercicedb.domain.data.CurrencyData;
import com.example.exercicedb.domain.data.RateData;
import com.example.exercicedb.entity.RateEntity;
import com.example.exercicedb.library.AbstractDataAdapter;
import org.springframework.stereotype.Component;

@Component
public class RateRepositoryAdapter extends AbstractDataAdapter<RateData, RateEntity> {

    @Override
    public RateData fromTargetToSource(RateEntity entity) {
        return RateData.builder()
                .brandId(entity.getBrandId())
                .price(entity.getPrice())
                .endDate(entity.getEndDate())
                .startDate(entity.getStartDate())
                .productId(entity.getProductId())
                .currency(CurrencyData.builder().code(entity.getCurrencyCode()).build())
                .build();
    }

    @Override
    public RateEntity fromSourceToTarget(RateData data) {
        return RateEntity.builder()
                .brandId(data.getBrandId())
                .price(data.getPrice())
                .id(data.getId())
                .endDate(data.getEndDate())
                .startDate(data.getStartDate())
                .currencyCode(data.getCurrency().getCode())
                .productId(data.getProductId())
                .build();
    }

}
