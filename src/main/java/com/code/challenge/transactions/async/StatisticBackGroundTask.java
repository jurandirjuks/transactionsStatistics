package com.code.challenge.transactions.async;

import com.code.challenge.transactions.actors.TransactionTimeActor;
import com.code.challenge.transactions.model.Statistics;
import com.code.challenge.transactions.model.Transaction;
import com.code.challenge.transactions.model.TransactionCache;
import com.code.challenge.transactions.actors.StatisticCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticBackGroundTask {

    @Autowired
    private TransactionCache transactionCache;

    @Autowired
    private StatisticCalculator staticsCalculator;

    @Autowired
    private TransactionTimeActor transactionTimeActor;

    @Scheduled(fixedRate = 1000)
    public void run() {
        List<Transaction> transactions = this.getLastTransactions();
        Statistics calculate = staticsCalculator.calculate(transactions);
        transactionCache.update(calculate);
    }

    private List<Transaction> getLastTransactions() {
        return transactionCache.getTransactions().stream()
                .filter(t -> !transactionTimeActor.isTransactionTimeOlderThanAccepted(t.getTransactionTime()))
                .collect(Collectors.toList());
    }

}
