package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.repository.SecurityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityService {
    private SecurityRepository securityRepository;
}
