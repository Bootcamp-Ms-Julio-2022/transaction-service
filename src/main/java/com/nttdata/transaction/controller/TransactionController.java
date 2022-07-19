package com.nttdata.transaction.controller;

import com.nttdata.transaction.entity.Transaction;
import com.nttdata.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // -------------------Retrieve all purchases

    @GetMapping
    public Flux<Transaction> retrieveAll() {
        log.info("Retrieving all transactions");
        return transactionService.findAll();
    }

    // -------------------Retrieve single transaction by id

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Transaction>> retrieveById(@PathVariable("id") String id) {
        log.info("Retrieving transaction with id: " + id);
        Mono<Transaction> transaction = transactionService.findById(id);
        return transaction.map(p -> ResponseEntity.ok(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // -------------------Create a transaction

    @PostMapping
    public Mono<Transaction> save(@RequestBody Transaction transaction) {
        log.info("Registering new transaction - customer: " + transaction.getCustomerId() + ", purchase: " + transaction.getPurchaseId());
        return transactionService.save(transaction);
    }

    // -------------------Update a transaction

    @PutMapping
    public Mono<ResponseEntity<Transaction>> update(@RequestBody Transaction transaction) {
        log.info("Updating transaction with id: " + transaction.getId());
        return transactionService.update(transaction)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // -------------------Delete a transaction

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        log.info("Deleting transaction with id: " + id);
        return transactionService.delete(id)
                .map(p -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
