package com.code.challenge.transactions.services;

import com.code.challenge.transactions.actors.TransactionTimeActor;
import com.code.challenge.transactions.model.Transaction;
import com.code.challenge.transactions.model.TransactionCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    public static final long TIMESTAMP = 1213541155L;

    public static final double AMOUNT = 50.0;

    @Mock
    private TransactionCache transactionCache;

    @Mock
    private TransactionTimeActor transactionTimeActor;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void shouldSaveTransaction(){
        when(transactionTimeActor.isTransactionTimeOlderThanAccepted(any(Instant.class))).thenReturn(false);
        transactionService.processTransaction(TIMESTAMP, AMOUNT);

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionCache,Mockito.times(1)).save(captor.capture());
        assertEquals(50.0,captor.getValue().getAmount().doubleValue(),0.0);
        assertEquals(TIMESTAMP,captor.getValue().getTransactionTime().toEpochMilli(),0.0);
    }

    @Test
    public void shouldNotSaveTransaction(){
        when(transactionTimeActor.isTransactionTimeOlderThanAccepted(any(Instant.class))).thenReturn(true);
        transactionService.processTransaction(TIMESTAMP, AMOUNT);

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionCache,Mockito.times(0)).save(captor.capture());
    }
}