package com.example.exercicedb.repository;

import com.example.exercicedb.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface RateRepository extends JpaRepository<RateEntity, Integer> {

    @Query("select r from RateEntity r where r.startDate >= :date and r.endDate <= :date and r.brandId  = :brandId and r.productId = :productId")
    RateEntity getRateByFilter(LocalDate date, Integer productId, Integer brandId);

    @Modifying
    @Query("update RateEntity u set u.price = :price where u.id = :id")
    void updatePrice(@Param(value = "id") Integer id, @Param(value = "price") Integer price);
}
