package com.code.challenge.transactions.rest;

import com.code.challenge.transactions.model.Statistics;
import com.code.challenge.transactions.rest.modelview.TransactionBody;
import com.code.challenge.transactions.services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsResource {

    @Autowired
    private TransactionServiceImpl transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity processTransaction(@RequestBody TransactionBody transactionBody) {
        boolean computed = transactionService.processTransaction(transactionBody.getTimestamp(), transactionBody.getAmount());
        if (computed)
            return ResponseEntity.status(HttpStatus.valueOf(201)).build();

        return ResponseEntity.status(HttpStatus.valueOf(204)).build();
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    private ResponseEntity<Statistics> getStatistics(){
        return ResponseEntity.ok(transactionService.generateStatistics());
    }

}
