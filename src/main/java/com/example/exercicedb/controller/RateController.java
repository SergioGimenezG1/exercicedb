package com.example.exercicedb.controller;


import com.example.exercicedb.adapter.response.RateResponseAdapter;
import com.example.exercicedb.controller.ConstantsPaths.Paths;
import com.example.exercicedb.controller.ConstantsPaths.Tags;
import com.example.exercicedb.domain.data.CurrencyData;
import com.example.exercicedb.domain.data.RateData;
import com.example.exercicedb.domain.data.RateFilterData;
import com.example.exercicedb.domain.request.RateRequest;
import com.example.exercicedb.domain.response.RateResponse;
import com.example.exercicedb.service.RateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Api(tags = Tags.RATE)
@RestController
@Slf4j
@RequestMapping(value = Paths.Rate.RATE)
public class RateController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RateService rateService;

    @Autowired
    private RateResponseAdapter rateResponseAdapter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RateResponse createRate(@RequestBody @Valid RateRequest request) {
        log.info("Init RateController.createRate: Request {}", request);

        RateData requestData = objectMapper.convertValue(request, RateData.class);
        requestData.setCurrency(CurrencyData.builder()
                .code(request.getCurrencyCode())
                .build());

        RateData rateData = rateService.createRate(requestData);
        return rateResponseAdapter.fromSourceToTarget(rateData);
    }

    @PatchMapping(value = Paths.Rate.UPDATE_PRICE)
    public ResponseEntity<Void> updatePrice(@PathVariable Integer id, @PathVariable Integer price) {
        log.info("Init RateController.updatePrice: id {} price {}", id, price);
        rateService.updatePrice(id, price);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(value = Paths.PATH_ID)
    public void deleteRate(@PathVariable Integer id) {
        log.info("Init RateController.deleteRate: id {}", id);
        rateService.deleteRate(id);
    }

    @GetMapping(value = Paths.PATH_ID)
    public ResponseEntity<RateResponse> getRateById(@PathVariable Integer id) {
        log.info("Init RateController.getRateById: id {}", id);
        RateData rateData = rateService.getRateById(id);
        return new ResponseEntity<>(rateResponseAdapter.fromSourceToTarget(rateData), HttpStatus.OK);
    }


    @ApiOperation(value = "Endpoint to request list of assignments by filters")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Endpoint to request pageable lis of assignments")})
    @GetMapping
    public ResponseEntity<RateResponse> getRateByFilter(
            @ApiParam(value = "Equal date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @ApiParam(value = "Equal brand id") Integer brandId,
            @ApiParam(value = "Equal product id") Integer productId
    ) {
        log.info("Init RateController.getRateByFilter");

        RateData rateData = rateService.getRateByFilter(RateFilterData.builder()
                .brandId(brandId)
                .productId(productId)
                .date(date)
                .build());

        return new ResponseEntity<>(rateResponseAdapter.fromSourceToTarget(rateData), HttpStatus.OK);
    }


}
