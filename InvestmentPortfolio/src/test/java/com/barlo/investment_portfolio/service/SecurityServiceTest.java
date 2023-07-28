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
import java.util.Collections;
import java.util.NoSuchElementException;

@SpringBootTest
@Sql(scripts = "classpath:db/populateH2.sql")
class SecurityServiceTest extends AbstractTest {

    @Autowired
    SecurityService securityService;

    @Test
    @Transactional
    void getAllSecurities() {
        Assertions.assertIterableEquals(securityService.getAll(), Arrays.asList(security_1, security_2));
    }

    @Test
    @Transactional
    void getAllSecuritiesForPortfolio() {
        Assertions.assertIterableEquals(securityService.getAllForPortfolio(security_1.getPortfolio().getId()), Collections.singletonList(security_1));
    }

    @Test
    @Transactional
    void getAllSecuritiesForPortfolioNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
           securityService.getAllForPortfolio(3L);
        });
    }

    @Test
    @Transactional
    void getSecurityByNameForPortfolio() {
        Assertions.assertEquals(securityService.getByNameForPortfolio(security_1.getPortfolio().getId(), security_1.getName()), security_1);
    }

    @Test
    @Transactional
    void getSecurityByNameForPortfolioNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            securityService.getByNameForPortfolio(0L,"GTLR");
        });
    }

    @Test
    @Transactional
    void getAllByName() {
        Assertions.assertIterableEquals(securityService.getAllByName(security_1.getName()), Arrays.asList(security_1, security_2));
    }
}