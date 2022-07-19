package com.nttdata.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "transaction")
public class Transaction {

    @Id
    private String id;

    private String customerId;

    private String purchaseId;

    private String source;

    private String transactionType;

    private Date emittedAt;

    private Double amount;

    private String state;

}
