package com.example.demo.service.impl;

import com.example.demo.entity.BuyBootCoin;
import com.example.demo.repository.BuyBootCoinRepository;
import com.example.demo.service.BuyBootCoinService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BuyBootCoinServiceImpl implements BuyBootCoinService {

    private static final Logger log = LogManager.getLogger(BuyBootCoinService.class);

    @Autowired
    BuyBootCoinRepository buyBootCoinRepository;

    @Override
    public Flux<BuyBootCoin> findAll() {
        log.info("Method call FindAll - customer");
        return buyBootCoinRepository.findAll();
    }

    @Override
    public Mono<BuyBootCoin> create(BuyBootCoin b) {
        log.info("Method call Create - customer");
        return buyBootCoinRepository.save(b);
    }

    @Override
    public Mono<BuyBootCoin> findById(String id) {
        log.info("Method call FindById - customer");
        return buyBootCoinRepository.findById(id);
    }

    @Override
    public Mono<BuyBootCoin> update(BuyBootCoin b, String id) {
        log.info("Method call Update - customer");
         return buyBootCoinRepository.findById(id)
                .map( x -> {
                    x.setWalletId(b.getWalletId());
                    x.setCustomerIdEmisor(b.getCustomerIdEmisor());
                    x.setCustomerIdReceptor(b.getCustomerIdReceptor());
                    x.setMonto(b.getMonto());
                    x.setState(b.getState());
                    return x;
                }).flatMap(buyBootCoinRepository::save);
    }

    @Override
    public Mono<BuyBootCoin> delete(String id) {
        log.info("Method call Delete - customer");
        return buyBootCoinRepository.findById(id).flatMap(
                x -> buyBootCoinRepository.delete(x).then(Mono.just(new BuyBootCoin())));
    }
}
