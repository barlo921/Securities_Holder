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
        StringBuilder logMessage = new StringBuilder("Finding all deals; Results: ");
        return ServiceUtils.checkListIsPresent(dealList, logMessage);
    }

    public Deal getById(Long id) {
        Optional<Deal> deal = dealRepository.findById(id);
        StringBuilder logMessage = new StringBuilder("Finding deal with id: " + id + "; Results: ");
        return ServiceUtils.checkIsPresent(deal, logMessage);
    }

    public List<Deal> getAllForSecurityById(Long id) {
        List<Deal> dealList = dealRepository.findAllBySecurityId(id);
        StringBuilder logMessage = new StringBuilder("Finding all deals with security id: " + id + "; Results: ");
        return ServiceUtils.checkListIsPresent(dealList, logMessage);
    }
}
