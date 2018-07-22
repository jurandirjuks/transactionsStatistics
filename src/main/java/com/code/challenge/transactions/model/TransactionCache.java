package com.code.challenge.transactions.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;


@Component
public class TransactionCache {

    private static ConcurrentLinkedDeque<Transaction> cache = new ConcurrentLinkedDeque<>();
    private static ConcurrentLinkedDeque<Statistics> statisticsCache = new ConcurrentLinkedDeque<>();

    public void save(Transaction transaction) {
        cache.add(transaction);
    }

    public Statistics getStatistics() {
        return statisticsCache.isEmpty() ? new Statistics() : statisticsCache.getLast();
    }

    public void update(Statistics statistics) {
        statisticsCache.addLast(statistics);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(cache);
    }
}
