package com.barlo.investment_portfolio;

import com.barlo.investment_portfolio.model.Deal;
import com.barlo.investment_portfolio.model.Portfolio;
import com.barlo.investment_portfolio.model.Security;
import com.barlo.investment_portfolio.model.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@ExtendWith(TestResults.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {

    protected Deal deal_1;
    protected Deal deal_2;
    protected Security security_1;
    protected Security security_2;
    protected Portfolio portfolio_1;
    protected Portfolio portfolio_2;

    @BeforeAll
    void init() {
        deal_1 = Deal
                .builder()
                .id(5L)
                .dateTime(LocalDateTime.of(2023, 01, 01, 00, 01,01))
                .price(2400)
                .quantity(1)
                .build();

        deal_2 = Deal
                .builder()
                .id(6L)
                .dateTime(LocalDateTime.of(2023, 01, 01, 00, 01,02))
                .price(2399)
                .quantity(1)
                .build();

        security_1 = Security
                .builder()
                .id(3L)
                .name("YNDX")
                .averagePrice(2400)
                .quantity(1)
                .type(Type.SHARE)
                .build();

        security_1.setDealsHistory(Arrays.asList(deal_1, deal_2));
        deal_1.setSecurity(security_1);
        deal_2.setSecurity(security_1);

        portfolio_1 = Portfolio
                .builder()
                .id(1L)
                .userId(1L)
                .securities(Collections.singletonList(security_1))
                .build();

        security_1.setPortfolio(portfolio_1);

        security_2 = Security
                .builder()
                .id(4L)
                .name("YNDX")
                .averagePrice(0)
                .quantity(0)
                .type(Type.SHARE)
                .dealsHistory(new ArrayList<Deal>())
                .build();

        portfolio_2 = Portfolio
                .builder()
                .id(2L)
                .userId(2L)
                .securities(Collections.singletonList(security_2))
                .build();

        security_2.setPortfolio(portfolio_2);
    }


}
