package com.nttdata.transaction.service;

import com.nttdata.transaction.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<Transaction> findAll();

    Mono<Transaction> findById(String id);

    Mono<Transaction> save(Transaction transaction);

    Mono<Transaction> update(Transaction transaction);

    Mono<Transaction> delete(String id);
}
