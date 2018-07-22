package com.code.challenge.transactions.actors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTimeActorTest {

    @InjectMocks
    private TransactionTimeActor transactionTimeActor;

    @Test
    public void shouldReturnTrue(){
        boolean transactionTImeOlderThanAccepted = transactionTimeActor.isTransactionTimeOlderThanAccepted(Instant.now().minusSeconds(100));
        assertTrue(transactionTImeOlderThanAccepted);
    }

    @Test
    public void shouldReturnFalse(){
        boolean transactionTImeOlderThanAccepted = transactionTimeActor.isTransactionTimeOlderThanAccepted(Instant.now().minusSeconds(30));
        assertFalse(transactionTImeOlderThanAccepted);
    }

}