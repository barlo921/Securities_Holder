package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.AbstractTest;
import com.barlo.investment_portfolio.model.Portfolio;
import com.barlo.investment_portfolio.model.Security;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@Sql(scripts = "classpath:db/populateH2.sql")
class PortfolioServiceTest extends AbstractTest {

    @Autowired
    PortfolioService portfolioService;

    @Test
    @Transactional
    void getAllPortfolios() {
        Assertions.assertIterableEquals(portfolioService.getAll(), Arrays.asList(portfolio_1, portfolio_2));
    }

    @Test
    @Transactional
    void getPortfolioById() {
        Assertions.assertEquals(portfolioService.getById(portfolio_1.getId()), portfolio_1);
    }

    @Test
    void create() {
        Portfolio newPortfolio =
                Portfolio
                        .builder()
                        .securities(new ArrayList<Security>())
                        .build();

        Portfolio createdPortfolio = portfolioService.create(newPortfolio, USER_3);
        newPortfolio.setId(createdPortfolio.getId());
        Assertions.assertEquals(createdPortfolio, newPortfolio);
    }

    @Test
    void createWithNullPortfolio() {
        Portfolio portfolio = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           portfolioService.create(portfolio, USER_3);
        });
    }
}