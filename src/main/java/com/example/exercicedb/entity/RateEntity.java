package com.example.exercicedb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "T_RATES")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RateEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Column(name = "BRAND_ID")
    private Integer brandId;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

}