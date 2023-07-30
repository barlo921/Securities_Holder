package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.Portfolio;
import com.barlo.investment_portfolio.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        return ServiceUtils.checkListIsPresent(portfolioList, logMessage);
    }

    public Portfolio getById(Long id) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        StringBuilder logMessage = new StringBuilder("Finding portfolio with id: " + id + "; Results: ");
        return ServiceUtils.checkIsPresent(portfolio, logMessage);
    }

    public List<Portfolio> getByUserId(Long id) {
        List<Portfolio> portfolioList = portfolioRepository.findByUserId(id);
        StringBuilder logMessage = new StringBuilder("Finding all portfolios with user id: " + id + "; Result: ");
        return ServiceUtils.checkListIsPresent(portfolioList, logMessage);
    }

    public  Portfolio getByIdWithUserId(Long id, Long userId) {
        Optional<Portfolio> portfolio = portfolioRepository.findByIdAndUserId(id, userId);
        StringBuilder logMessage = new StringBuilder("Finding portfolio with id: " + id + ", and user id:" + userId + "; Result: ");
        return ServiceUtils.checkIsPresent(portfolio, logMessage);
    }

    public Portfolio create(Portfolio portfolio, Long userId) {
        Assert.notNull(portfolio, "Portfolio must not be null");
        portfolio.setUserId(userId);
        log.info("Creating new portfolio for user with id: {}", userId);
        return portfolioRepository.save(portfolio);
    }

    public Portfolio update(Portfolio portfolio, Long userId) {
        Assert.notNull(portfolio, "Portfolio must not be null");
        StringBuilder logMessage = new StringBuilder("Check portfolio with id: " + portfolio.getId() + ", and user id:" + userId + "; Result: ");
        ServiceUtils.checkIsPresent(portfolioRepository.findByIdAndUserId(portfolio.getId(), userId), logMessage);
        log.info("Updating portfolio with id: {}", portfolio.getId());
        return portfolioRepository.save(portfolio);
    }

    public void delete(Long id) {
        StringBuilder logMessage = new StringBuilder("Check portfolio with id: " + id + "; Result: ");
        ServiceUtils.checkIsPresent(portfolioRepository.findById(id), logMessage);
        log.info("Deleting portfolio with id: {}", id);
        portfolioRepository.deleteById(id);
    }

}
