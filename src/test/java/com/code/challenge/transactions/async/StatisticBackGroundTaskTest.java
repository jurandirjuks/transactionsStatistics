package com.code.challenge.transactions.async;

import com.code.challenge.transactions.actors.TransactionTimeActor;
import com.code.challenge.transactions.model.Transaction;
import com.code.challenge.transactions.model.TransactionCache;
import com.code.challenge.transactions.actors.StatisticCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticBackGroundTaskTest {

    @Mock
    private TransactionCache transactionCache;

    @Mock
    private StatisticCalculator staticsCalculator;

    @Mock
    private TransactionTimeActor transactionTimeActor;

    @InjectMocks
    private StatisticBackGroundTask statisticBackGroundTask;

    @Test
    public void shouldSendAllTransactions() {
        ArrayList<Transaction> transactions = getTransactions();
        when(transactionCache.getTransactions()).thenReturn(transactions);
        when(transactionTimeActor.isTransactionTimeOlderThanAccepted(any(Instant.class))).thenReturn(false);

        statisticBackGroundTask.run();

        verify(staticsCalculator, Mockito.times(1)).calculate(transactions);
    }

    @Test
    public void shouldFilterAllTransactions() {
        ArrayList<Transaction> transactions = getTransactions();
        when(transactionCache.getTransactions()).thenReturn(transactions);
        when(transactionTimeActor.isTransactionTimeOlderThanAccepted(any(Instant.class))).thenReturn(true);

        statisticBackGroundTask.run();

        ArgumentCaptor<ArrayList> captor = ArgumentCaptor.forClass(ArrayList.class);

        verify(staticsCalculator, Mockito.times(1)).calculate((List<Transaction>) captor.capture());

        assertEquals(0,captor.getValue().size());
    }

    private ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions =  new ArrayList();
        Transaction transaction = new Transaction(Instant.now(),10.0);
        transactions.add(transaction);

        transaction = new Transaction(Instant.now(),20.0);
        transactions.add(transaction);

        transaction = new Transaction(Instant.now(),30.0);
        transactions.add(transaction);

        transaction = new Transaction(Instant.now(),40.0);
        transactions.add(transaction);

        return transactions;
    }
}