package com.code.challenge.transactions.actors;

import com.code.challenge.transactions.actors.StatisticCalculatorImpl;
import com.code.challenge.transactions.model.Statistics;
import com.code.challenge.transactions.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StatisticCalculatorImplTest {

    @InjectMocks
    private StatisticCalculatorImpl statisticCalculator;

    @Test
    public void shouldCalculate() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Instant.now(),30.0));
        transactions.add(new Transaction(Instant.now(),70.0));
        transactions.add(new Transaction(Instant.now(),20.0));
        transactions.add(new Transaction(Instant.now(),80.0));

        Statistics calculate = statisticCalculator.calculate(transactions);

        assertEquals(50.0,calculate.getAvg().doubleValue(),0.0);
        assertEquals(80.0,calculate.getMax().doubleValue(),0.0);
        assertEquals(20.0,calculate.getMin().doubleValue(),0.0);
        assertEquals(4L,calculate.getCount().longValue());
    }

    @Test
    public void shouldCalculateEmpty() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Statistics calculate = statisticCalculator.calculate(transactions);

        assertEquals(0.0,calculate.getAvg().doubleValue(),0.0);
        assertEquals(0.0,calculate.getMax().doubleValue(),0.0);
        assertEquals(0.0,calculate.getMin().doubleValue(),0.0);
        assertEquals(0L,calculate.getCount().longValue());
    }
}