package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@SpringBootTest
@Sql(scripts = "classpath:db/populateH2.sql")
class DealServiceTest extends AbstractTest{

    @Autowired
    DealService dealService;

    @Test
    @Transactional
    void getAllDeals() {
        Assertions.assertIterableEquals(dealService.getAll(), Collections.singletonList(deal));
    }


    @Test
    @Transactional
    void getAllDealsForSecurityById() {
        Assertions.assertIterableEquals(dealService.getAllForSecurityById(deal.getSecurity().getId()).orElseThrow(), Collections.singletonList(deal));
    }
}