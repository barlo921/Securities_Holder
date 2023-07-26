package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.Deal;
import com.barlo.investment_portfolio.repository.DealRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DealService {

    private DealRepository dealRepository;

    public List<Deal> getAll() {
        List<Deal> dealList = dealRepository.findAll();
        log.info("Finding all deals. Results: {}", dealList);
        return dealList;
    }

    public Optional<List<Deal>> getAllForSecurityById(Long id) {
        Optional<List<Deal>> dealList = dealRepository.findAllBySecurityId(id);
        log.info("Finding all with security id: {}. Results: {}", id, dealList);
        return dealList;
    }

    public Optional<List<Deal>> getAllForSecurityByName(String name) {
        Optional<List<Deal>> dealList = dealRepository.findAllBySecurityName(name);
        log.info("Finding all with security name: {}. Results: {}", name, dealList);
        return dealList;
    }
}
