package com.example.demo.service.impl;

import com.example.demo.entity.Exchange;
import com.example.demo.repository.ExchangeRepository;
import com.example.demo.service.ExchangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private static final Logger log = LogManager.getLogger(ExchangeServiceImpl.class);
    @Autowired
    ExchangeRepository exchangeRepository;

    @Override
    public Flux<Exchange> findAll() {
        log.info("Method call FindAll - Exchange");
        return exchangeRepository.findAll();
    }

    @Override
    public Mono<Exchange> create(Exchange e) {
        log.info("Method call Create - Exchange");
        return exchangeRepository.save(e);
    }

    @Override
    public Mono<Exchange> findById(String id) {
        log.info("Method call FindById - Exchange");
        return exchangeRepository.findById(id);
    }

    @Override
    public Mono<Exchange> update(Exchange e, String id) {
        log.info("Method call Update - Exchange");
        return exchangeRepository.findById(id)
                .map( x -> {
                    x.setCustomerId(e.getCustomerId());
                    x.setMontoSoles(e.getMontoSoles());
                    x.setMontoBootCoin(e.getMontoBootCoin());
                    return x;
                }).flatMap(exchangeRepository::save);
    }

    @Override
    public Mono<Exchange> delete(String id) {
        log.info("Method call Delete - Exchange");
        return exchangeRepository.findById(id).flatMap(
                x -> exchangeRepository.delete(x).then(Mono.just(new Exchange())));
    }
}
