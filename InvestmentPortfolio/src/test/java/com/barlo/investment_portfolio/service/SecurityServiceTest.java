package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:db/populateH2.sql")
class SecurityServiceTest extends AbstractTest {

    @Autowired
    SecurityService securityService;

    @Test
    @Transactional
    void getAllSecurities() {
        Assertions.assertIterableEquals(securityService.getAll(), Collections.singletonList(security));
    }

    @Test
    @Transactional
    void getAllSecuritiesForPortfolio() {
        Assertions.assertIterableEquals(securityService.getAllForPortfolio(security.getPortfolio().getId()), Collections.singletonList(security));
    }

    @Test
    @Transactional
    void findSecurityByNameForPortfolio() {
        Assertions.assertEquals(securityService.findByNameForPortfolio(security.getPortfolio().getId(), security.getName()).orElseThrow(), security);
    }
}