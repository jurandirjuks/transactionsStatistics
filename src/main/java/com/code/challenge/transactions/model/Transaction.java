package com.code.challenge.transactions.model;

import java.time.Instant;

public class Transaction {
    private Instant transactionTime;
    private Double amount;

    public Transaction(Instant transactionTime, Double amount) {
        this.transactionTime = transactionTime;
        this.amount = amount;
    }

    public Instant getTransactionTime() {
        return transactionTime;
    }

    public Double getAmount() {
        return amount;
    }
}
