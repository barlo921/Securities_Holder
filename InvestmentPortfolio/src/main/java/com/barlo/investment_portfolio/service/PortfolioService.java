package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.Portfolio;
import com.barlo.investment_portfolio.model.Security;
import com.barlo.investment_portfolio.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    private PortfolioRepository portfolioRepository;
    private SecurityService securityService;

    public List<Portfolio> getAll() {
        List<Portfolio> portfolioList = portfolioRepository.findAll();
        log.info("Finding all portfolios. Result: {}", portfolioList);
        return portfolioList;
    }

    public Portfolio getById(Long id) {
        Portfolio portfolio = portfolioRepository.getById(id);
        log.info("Get portfolio with id: {}. Results: {}", id, portfolio);
        return portfolio;
    }

}
