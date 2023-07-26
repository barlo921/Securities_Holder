package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.Security;
import com.barlo.investment_portfolio.repository.SecurityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityService {

    private SecurityRepository securityRepository;

    public List<Security> getAll() {
        List<Security> securityList = securityRepository.findAll();
        log.info("Finding all securities. Results: {}", securityList);
        return securityList;
    }

    public List<Security> getAllForPortfolio(Long id) {
        List<Security> securityList = securityRepository.findByPortfolioId(id);
        log.info("Finding all securities with portfolio id: {}. Results: {}", id, securityList);
        return securityList;
    }

    public Optional<Security> findByNameForPortfolio(Long id, String name) {
        Optional<Security> security = securityRepository.findByPortfolioIdAndName(id, name);
        log.info("Finding security with portfolio id: {}, and name: {}. Results: {} ", id, name, security);
        return security;
    }
}
