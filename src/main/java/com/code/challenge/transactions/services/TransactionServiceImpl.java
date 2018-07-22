package com.code.challenge.transactions.services;

import com.code.challenge.transactions.actors.TransactionTimeActor;
import com.code.challenge.transactions.model.Statistics;
import com.code.challenge.transactions.model.Transaction;
import com.code.challenge.transactions.model.TransactionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionCache transactionCache;

    @Autowired
    private TransactionTimeActor transactionTimeActor;

    @Override
    public boolean processTransaction(Long timestamp, Double amount) {
        Instant transactionTime = Instant.ofEpochMilli(timestamp);
        if (transactionWillNotBeProcessed(transactionTime))
            return false;

        this.transactionCache.save(new Transaction(transactionTime, amount));
        return true;
    }

    @Override
    public Statistics generateStatistics() {
        return transactionCache.getStatistics();
    }

    private boolean transactionWillNotBeProcessed(Instant transactionTime) {
        return transactionTimeActor.isTransactionTimeOlderThanAccepted(transactionTime);
    }
}
