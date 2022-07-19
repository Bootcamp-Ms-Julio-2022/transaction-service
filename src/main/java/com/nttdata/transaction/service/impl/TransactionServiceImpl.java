package com.nttdata.transaction.service.impl;

import com.nttdata.transaction.entity.Transaction;
import com.nttdata.transaction.repository.TransactionRepository;
import com.nttdata.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        transaction.setEmittedAt(new Date());
        transaction.setState("validated");
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return transactionRepository.findById(transaction.getId())
                .flatMap(t -> {
                    t.setCustomerId(transaction.getCustomerId());
                    t.setPurchaseId(transaction.getPurchaseId());
                    t.setSource(transaction.getSource());
                    t.setTransactionType(transaction.getTransactionType());
                    t.setAmount(transaction.getAmount());
                    t.setState(transaction.getState());
                    return transactionRepository.save(t);
                });
    }

    @Override
    public Mono<Transaction> delete(String id) {
        return transactionRepository.findById(id)
                .flatMap(t -> transactionRepository.delete(t)
                        .then(Mono.just(t)));
    }
}
