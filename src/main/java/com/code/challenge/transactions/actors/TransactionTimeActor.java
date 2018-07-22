package com.code.challenge.transactions.actors;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransactionTimeActor {

    public static final int BASE_SECONDS = 60;

    public  boolean isTransactionTimeOlderThanAccepted(Instant transactionTime) {
        return Instant.now().minusSeconds(BASE_SECONDS).compareTo(transactionTime) == 1;
    }
}
