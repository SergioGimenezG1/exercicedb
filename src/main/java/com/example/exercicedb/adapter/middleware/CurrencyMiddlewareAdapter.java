package com.example.exercicedb.adapter.middleware;

import com.example.exercicedb.domain.data.CurrencyData;
import com.example.exercicedb.library.AbstractDataAdapter;
import com.example.exercicedb.middleware.data.CurrencyDto;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMiddlewareAdapter extends AbstractDataAdapter<CurrencyDto, CurrencyData> {

    @Override
    public CurrencyData fromSourceToTarget(CurrencyDto currencyDto) {
        return getObjectMapper().convertValue(currencyDto,CurrencyData.class);
    }

}
