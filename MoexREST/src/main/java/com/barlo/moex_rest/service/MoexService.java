package com.barlo.moex_rest.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MoexService implements MoexServiceInterface {
    private final WebClient webClient;

    @Override
    public Mono<String> find(String security) {
        return webClient
                .get()
                .uri(String.join("", "/securities.json?iss.meta=off&q=", security))
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> findBySecId(String secId) {
        return webClient
                .get()
                .uri(String.join("", "/securities/", secId,".json?iss.meta=off&description.columns=name,title,value&boards.columns=boardid,title,market,engine"))
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> findStockPrice(String secId, String market, String board) {
        return webClient
                .get()
                .uri(String.join("", "/engines/stock/markets/", market, "/boards/", board,"/securities/", secId,".json?iss.meta=off&iss.only=marketdata&marketdata.columns=OPEN,LOW,HIGH,LAST"))
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> getStockShortName(String secId) {
        return webClient
                .get()
                .uri(String.join("", "securities/", secId,".json?iss.meta=off&iss.only=description&description.columns=value"))
                .retrieve()
                .bodyToMono(String.class);
    }
}
