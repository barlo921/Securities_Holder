package com.barlo.investment_portfolio;

import com.barlo.investment_portfolio.model.Deal;
import com.barlo.investment_portfolio.model.Portfolio;
import com.barlo.investment_portfolio.model.Security;
import com.barlo.investment_portfolio.model.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Collections;


@ExtendWith(TestResults.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {

    protected Deal deal;
    protected Security security;
    protected Portfolio portfolio;

    @BeforeAll
    void init() {
        deal = Deal
                .builder()
                .id(3L)
                .dateTime(LocalDateTime.of(2023, 01, 01, 00, 01,01))
                .price(2400)
                .quantity(1)
                .build();

        security = Security
                .builder()
                .id(2L)
                .name("YNDX")
                .averagePrice(2400)
                .quantity(1)
                .type(Type.SHARE)
                .build();

        security.setDealsHistory(Collections.singletonList(deal));
        deal.setSecurity(security);

        portfolio = Portfolio
                .builder()
                .id(1L)
                .userId(1L)
                .securities(Collections.singletonList(security))
                .build();

        security.setPortfolio(portfolio);
    }


}
