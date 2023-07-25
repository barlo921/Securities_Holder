package com.barlo.investment_portfolio.repository;

import com.barlo.investment_portfolio.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, Long> {
}
