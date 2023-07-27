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
class PortfolioServiceTest extends AbstractTest {

    @Autowired
    PortfolioService portfolioService;

    @Test
    @Transactional
    void getAllPortfolios() {
        Assertions.assertIterableEquals(portfolioService.getAll(), Collections.singletonList(portfolio));
    }

    @Test
    @Transactional
    void getPortfolioById() {
        Assertions.assertEquals(portfolioService.getById(portfolio.getId()), portfolio);
    }
}