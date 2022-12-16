package com.example.exercicedb.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateFilterData implements Serializable {

    private Integer brandId;
    private Integer productId;
    private LocalDate date;

}