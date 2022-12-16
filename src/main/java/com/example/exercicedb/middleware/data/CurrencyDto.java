package com.example.exercicedb.middleware.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto implements Serializable {

    private String symbol;
    private String code;
    private Integer decimals;

}