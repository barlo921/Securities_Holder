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
        StringBuilder logMessage = new StringBuilder("Finding all securities; Results: ");
        return ServiceUtils.checkListIsPresent(securityList, logMessage);
    }

    public List<Security> getAllForPortfolio(Long id) {
        List<Security> securityList = securityRepository.findByPortfolioId(id);
        StringBuilder logMessage = new StringBuilder("Finding all securities with portfolio id: " + id + "; Results: ");
        return ServiceUtils.checkListIsPresent(securityList, logMessage);
    }

    public List<Security> getAllByName(String name) {
        List<Security> securityList = securityRepository.findByName(name);
        StringBuilder logMessage = new StringBuilder("Finding all securities with portfolio name: " + name + "; Results: ");
        return ServiceUtils.checkListIsPresent(securityList, logMessage);
    }

    public Security getByNameForPortfolio(Long id, String name) {
        Optional<Security> security = securityRepository.findByPortfolioIdAndName(id, name);
        StringBuilder logMessage = new StringBuilder("Finding security with portfolio id: " + id + "; Results: ");
        return ServiceUtils.checkIsPresent(security, logMessage);
    }
}
