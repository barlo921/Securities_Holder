package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.Deal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;

import static com.barlo.investment_portfolio.data.DealTestData.DEAL_1;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DealServiceTest {

    @Autowired
    DealService dealService;

    @Test
    void getAll() {
        Assertions.assertEquals(dealService.getAll(), new ArrayList<Deal>(Collections.singleton(DEAL_1)));
    }

    @Test
    void getAllForSecurityById() {
    }

    @Test
    void getAllForSecurityByName() {
    }
}