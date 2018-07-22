package com.code.challenge.transactions.services;

import com.code.challenge.transactions.model.Statistics;

public interface TransactionService {
    boolean processTransaction(Long timestamp, Double amount);

    Statistics generateStatistics();
}
