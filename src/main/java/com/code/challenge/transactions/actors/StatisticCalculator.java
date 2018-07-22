package com.code.challenge.transactions.actors;

import com.code.challenge.transactions.model.Statistics;
import com.code.challenge.transactions.model.Transaction;

import java.util.List;

public interface StatisticCalculator {
    Statistics calculate(List<Transaction> transactions);
}
