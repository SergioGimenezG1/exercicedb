package com.example.exercicedb.adapter.response;

import com.example.exercicedb.domain.data.RateData;
import com.example.exercicedb.domain.response.RateResponse;
import com.example.exercicedb.library.AbstractDataAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class RateResponseAdapter extends AbstractDataAdapter<RateData, RateResponse> {

    private static String DEFAULT_CURRENCY_SPLIT= ",";

    @Override
    public RateResponse fromSourceToTarget(RateData data) {
        return RateResponse.builder()
                .brandId(data.getBrandId())
                .endDate(data.getEndDate())
                .id(data.getId())
                .productId(data.getProductId())
                .currency(data.getCurrency().getCode())
                .price(getPriceByCurrency(data))
                .build();
    }

    private String getPriceByCurrency(RateData rateData) {
        String sPrice = rateData.getPrice().toString();
        int lengthPrice = sPrice.length();
        int indexDecimals = lengthPrice - rateData.getCurrency().getDecimals();
        String digits = sPrice.substring(0, indexDecimals);
        String digitsDecimals = StringUtils.reverse(sPrice).substring(0,indexDecimals);
        return String.format("%s%s%s %s",digits, DEFAULT_CURRENCY_SPLIT,digitsDecimals,rateData.getCurrency().getSymbol());
    }

}
