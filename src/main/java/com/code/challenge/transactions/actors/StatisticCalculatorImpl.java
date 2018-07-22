package com.code.challenge.transactions.actors;

import com.code.challenge.transactions.model.Statistics;
import com.code.challenge.transactions.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticCalculatorImpl implements StatisticCalculator {

    @Override
    public Statistics calculate(List<Transaction> transactions) {
        if (transactions.isEmpty())
            return new Statistics();

        Long count = Long.valueOf(transactions.size());
        Double avg = transactions.stream().mapToDouble(Transaction::getAmount).average().getAsDouble();
        Double sum​​ = transactions.stream().mapToDouble(Transaction::getAmount).sum();
        Double max = transactions.stream().mapToDouble(Transaction::getAmount).max().getAsDouble();
        Double min​​ = transactions.stream().mapToDouble(Transaction::getAmount).min().getAsDouble();
        return new Statistics(sum​​,avg,max,min​​,count);
    }


}
