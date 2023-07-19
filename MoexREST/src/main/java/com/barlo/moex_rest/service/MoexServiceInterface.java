package com.barlo.moex_rest.service;

import reactor.core.publisher.Mono;

public interface MoexServiceInterface {
    Mono<String> find(String security);
    Mono<String> findBySecId(String secId);
    Mono<String> findStockPrice(String secId, String market, String board);
    Mono<String> getStockShortName(String secId);
}
