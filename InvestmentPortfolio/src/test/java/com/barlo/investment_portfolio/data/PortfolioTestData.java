package com.barlo.investment_portfolio.data;

import com.barlo.investment_portfolio.model.Portfolio;

public class PortfolioTestData {
    public static final Portfolio PORTFOLIO_1 =
            Portfolio
                    .builder()
                    .id(1L)
                    .userId(1L)
                    .build();
}
