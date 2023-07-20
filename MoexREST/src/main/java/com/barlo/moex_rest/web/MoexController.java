package com.barlo.moex_rest.web;

import com.barlo.moex_rest.model.MarketData;
import com.barlo.moex_rest.model.Security;
import com.barlo.moex_rest.model.SecuritySpecification;
import com.barlo.moex_rest.service.DataService;
import com.barlo.moex_rest.service.MoexService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/moex/")
@AllArgsConstructor
public class MoexController {
    private MoexService moexService;
    private DataService dataService;

    @GetMapping("/find/{security}")
    public List<Security> findSecurities(@PathVariable("security") String security) throws JSONException {
        log.info("Search for: {}", security);
        Mono<String> mono = moexService.find(security);
        List<Security> securities = dataService.mapSecuritiesToList(mono.block(), security);
        log.info("Get results: {}", securities);
        return securities;
    }

    @GetMapping("/find/secId/{secid}")
    public SecuritySpecification findBySecId(@PathVariable("secid") String secid) throws JSONException {
        log.info("Search for secid: {}.", secid);
        Mono<String> mono = moexService.findBySecId(secid);
        SecuritySpecification securitySpecification = dataService.mapSecuritySpecification(mono.block(), secid);
        log.info("Get results: {}", securitySpecification);
        return securitySpecification;
    }

    @GetMapping("/find/marketData/secId/{secid}")
    public MarketData findMarketDataBySecId(@PathVariable("secid") String secid) throws JSONException {
        log.info("Get market data for secid: {}", secid);
        SecuritySpecification securitySpecification = findBySecId(secid);
        Mono<String> stockPrice = moexService.findStockPrice(secid, securitySpecification.getBoards().get(0).getMarket(), securitySpecification.getBoards().get(0).getBoardId());
        MarketData marketData = dataService.flatMarketData(stockPrice.block());
        log.info("Results: {}", marketData);
        return marketData;
    }

    @GetMapping("/find/lastPrice/secId/{secid}")
    public double findLastPrice(@PathVariable("secid") String secid) throws JSONException {
        log.info("Get last price for secid: {}", secid);
        MarketData marketData = findMarketDataBySecId(secid);
        log.info("Results: {}", marketData.getLast());
        return marketData.getLast();
    }

}
