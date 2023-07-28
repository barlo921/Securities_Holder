package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.AbstractTest;
import com.barlo.investment_portfolio.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SpringBootTest
@Sql(scripts = "classpath:db/populateH2.sql")
class DealServiceTest extends AbstractTest{

    @Autowired
    DealService dealService;

    @Test
    @Transactional
    void getAllDeals() {
        Assertions.assertIterableEquals(dealService.getAll(), Arrays.asList(deal_1, deal_2));
    }

    @Test
    void getById() {
        Assertions.assertEquals(dealService.getById(5L), deal_1);
    }

    @Test
    void getByIdNoSuchElement() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            dealService.getById(8L);
        });
    }


    @Test
    @Transactional
    void getAllDealsForSecurityById() {
        Assertions.assertIterableEquals(dealService.getAllForSecurityById(deal_1.getSecurity().getId()), Arrays.asList(deal_1, deal_2));
    }

    @Test
    @Transactional
    void getAllDealsForSecurityByIdNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            dealService.getAllForSecurityById(security_2.getId());
        });
    }
}