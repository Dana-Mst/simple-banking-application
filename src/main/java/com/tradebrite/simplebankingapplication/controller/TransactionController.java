package com.tradebrite.simplebankingapplication.controller;

import com.tradebrite.simplebankingapplication.DTO.DepositTransactionDTO;
import com.tradebrite.simplebankingapplication.DTO.TransferTransactionDTO;
import com.tradebrite.simplebankingapplication.DTO.WithdrawTransactionDTO;
import com.tradebrite.simplebankingapplication.model.GenericResponse;
import com.tradebrite.simplebankingapplication.model.errors.ErrorMessage;
import com.tradebrite.simplebankingapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping ("/deposit")
    public ResponseEntity depositMoney(@RequestBody @Valid DepositTransactionDTO depositTransactionDTO) throws Exception {
        Boolean result = transactionService.depositMoney(depositTransactionDTO);
        if(result == Boolean.FALSE) {
            return new ResponseEntity(ErrorMessage.ACCOUNT_NOT_FOUND_IN_DB, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);
    }


    @PutMapping("/withdraw")
    public ResponseEntity withdrawMoney(@RequestBody @Valid WithdrawTransactionDTO withdrawTransactionDTO) {
        GenericResponse result = transactionService.withdrawMoney(withdrawTransactionDTO);

        if(result.getStatus() != HttpStatus.OK) {
            return new ResponseEntity(result.getMessage(), new HttpHeaders(), result.getStatus());

        }
        return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity transferBetweenAccounts(@RequestBody @Valid TransferTransactionDTO transferTransactionDTO) {
        GenericResponse result = transactionService.transferBetweenAccounts(transferTransactionDTO);

        if(result.getStatus() != HttpStatus.OK) {
            return new ResponseEntity(result.getMessage(), new HttpHeaders(), result.getStatus());

        }
        return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);
    }



}
