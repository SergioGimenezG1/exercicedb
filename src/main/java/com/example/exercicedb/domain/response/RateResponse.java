package com.example.exercicedb.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Object for describing the fields of the rate")
public class RateResponse implements Serializable {

    @ApiModelProperty(value = "Identifier of rate")
    private Integer id;

    @ApiModelProperty(value = "Identifier of brand")
    private Integer brandId;
    @ApiModelProperty(value = "Identifier of product")
    private Integer productId;

    @ApiModelProperty(value = "Rate start date")
    private LocalDate startDate;

    @ApiModelProperty(value = "Rate end date")
    private LocalDate endDate;

    @ApiModelProperty(value = "Formatted price")
    private String price;

    @ApiModelProperty(value = "Currency's rate")
    private String currency;
}