package com.barlo.investment_portfolio.data;

import com.barlo.investment_portfolio.model.Deal;
import com.barlo.investment_portfolio.model.Security;

import java.time.LocalDateTime;

public class DealTestData {
    public static final Deal DEAL_1 =
            Deal
                    .builder()
                    .id(3L)
                    .dateTime(LocalDateTime.of(2023, 01, 01, 00, 01,01))
                    .price(2400)
                    .quantity(1L)
                    .security(Security.builder().id(2L).build())
                    .build();
}
