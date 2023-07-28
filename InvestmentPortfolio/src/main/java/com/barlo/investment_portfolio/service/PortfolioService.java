package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.Portfolio;
import com.barlo.investment_portfolio.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    private PortfolioRepository portfolioRepository;

    public List<Portfolio> getAll() {
        List<Portfolio> portfolioList = portfolioRepository.findAll();
        StringBuilder logMessage = new StringBuilder("Finding all portfolios; Result: ");
        ServiceUtils.checkListIsPresent(portfolioList, logMessage);
        log.info(logMessage.toString());
        return portfolioList;
    }

    public Portfolio getById(Long id) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        StringBuilder logMessage = new StringBuilder("Finding portfolio with id: " + id + "; Results: ");
        return ServiceUtils.checkIsPresent(portfolio, logMessage);
    }

}
