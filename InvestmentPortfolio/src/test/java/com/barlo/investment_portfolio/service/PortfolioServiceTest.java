package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.AbstractTest;
import com.barlo.investment_portfolio.exception.NotFoundException;
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
import java.util.Collections;

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
    void createPortfolio() {
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
    void createPortfolioWithNullPortfolio() {
        Portfolio portfolio = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           portfolioService.create(portfolio, USER_3);
        });
    }

    @Test
    @Transactional
    void updatePortfolio() {
        Portfolio newPortfolio =
                Portfolio
                        .builder()
                        .id(1L)
                        .userId(1L)
                        .securities(new ArrayList<Security>())
                        .build();

        Portfolio updatedPortfolio = portfolioService.update(newPortfolio, USER_1);
        Assertions.assertEquals(newPortfolio, updatedPortfolio);
    }

    @Test
    @Transactional
    void updatePortfolioWithWrongUserId() {
        Portfolio newPortfolio =
                Portfolio
                        .builder()
                        .id(1L)
                        .userId(2L)
                        .securities(new ArrayList<Security>())
                        .build();

        Assertions.assertThrows(NotFoundException.class, () -> {
            portfolioService.update(newPortfolio, USER_2);
        });
    }

    @Test
    @Transactional
    void updateNullPortfolio() {
        Portfolio newPortfolio = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            portfolioService.update(newPortfolio, USER_2);
        });
    }

    @Test
    @Transactional
    void getPortfoliosByUserId() {
        Assertions.assertIterableEquals(portfolioService.getByUserId(USER_1), Collections.singletonList(portfolio_1));
    }

    @Test
    @Transactional
    void getPortfoliosByUserIdNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            portfolioService.getByUserId(4L);
        });
    }

    @Test
    @Transactional
    void getPortfolioByIdWithUserId() {
        Assertions.assertEquals(portfolioService.getByIdWithUserId(portfolio_1.getId(), USER_1), portfolio_1);
    }

    @Test
    @Transactional
    void getPortfolioByIdWrongUserId() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            portfolioService.getByIdWithUserId(portfolio_1.getId(), USER_2);
        });
    }

    @Test
    @Transactional
    void getPortfolioByWrongIdWithUserId() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            portfolioService.getByIdWithUserId(portfolio_2.getId(), USER_1);
        });
    }

    @Test
    @Transactional
    void deletePortfolio() {
        portfolioService.delete(portfolio_1.getId());
        Assertions.assertThrows(NotFoundException.class, () -> {
           portfolioService.getById(portfolio_1.getId());
        });
    }
}