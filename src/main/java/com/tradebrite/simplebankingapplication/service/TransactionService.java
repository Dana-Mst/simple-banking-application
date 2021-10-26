package com.tradebrite.simplebankingapplication.service;

import com.tradebrite.simplebankingapplication.DTO.DepositTransactionDTO;
import com.tradebrite.simplebankingapplication.DTO.TransactionDTO;
import com.tradebrite.simplebankingapplication.DTO.TransferTransactionDTO;
import com.tradebrite.simplebankingapplication.DTO.WithdrawTransactionDTO;
import com.tradebrite.simplebankingapplication.model.AccountModel;
import com.tradebrite.simplebankingapplication.model.GenericResponse;
import com.tradebrite.simplebankingapplication.model.TransactionModel;
import com.tradebrite.simplebankingapplication.model.TransactionType;
import com.tradebrite.simplebankingapplication.model.errors.ErrorMessage;
import com.tradebrite.simplebankingapplication.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;


    public Boolean depositMoney(DepositTransactionDTO depositTransactionDTO)  {
        Optional<AccountModel> account = accountService.getAccount(depositTransactionDTO.getAccountId());
        return account.map(
                acc -> {
                    Double initialBalance = account.get().getCurrentBalance();
                    Double depositAmount = depositTransactionDTO.getAmount();
                    account.get().setCurrentBalance(initialBalance + depositAmount);

                    TransactionModel transactionToSave = convertToModel(depositTransactionDTO);
                    transactionToSave.setDate(Date.from(Instant.now()));
                    transactionToSave.setTransactionType(TransactionType.DEPOSIT);
                    saveTransaction(transactionToSave);

                    return Boolean.TRUE;
                }).orElse( Boolean.FALSE);

    }

    public GenericResponse withdrawMoney(WithdrawTransactionDTO withdrawTransactionDTO) {
        Optional<AccountModel> account = accountService.getAccount(withdrawTransactionDTO.getAccountId());
        return account.map(
                acc -> {

                    Double initialBalance = account.get().getCurrentBalance();
                    Double withdrawAmount = withdrawTransactionDTO.getAmount();
                    if (withdrawAmount > initialBalance) {
                        return GenericResponse.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(ErrorMessage.INSUFFICIENT_MONEY)
                                .build();

                    }
                    account.get().setCurrentBalance(initialBalance - withdrawAmount);

                    TransactionModel transactionToSave = convertToModel(withdrawTransactionDTO);
                    transactionToSave.setDate(Date.from(Instant.now()));
                    transactionToSave.setTransactionType(TransactionType.WITHDRAW);
                    saveTransaction(transactionToSave);

                    return GenericResponse.builder()
                            .status(HttpStatus.OK)
                            .build();
                }).orElse(GenericResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(ErrorMessage.ACCOUNT_NOT_FOUND_IN_DB)
                .build());
    }

    public GenericResponse transferBetweenAccounts(TransferTransactionDTO transferTransactionDTO) {
        Optional<AccountModel> fromAccount = accountService.getAccount(transferTransactionDTO.getAccountId());
        Optional<AccountModel> toAccount = accountService.getAccount(transferTransactionDTO.getToAccountId());

        if (fromAccount.isEmpty() || toAccount.isEmpty() ) {
            return GenericResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(ErrorMessage.ACCOUNT_NOT_FOUND_IN_DB)
                    .build();
        }
        Double initialBalanceFromAccount = fromAccount.get().getCurrentBalance();
        Double initialBalanceToAccount = toAccount.get().getCurrentBalance();
        Double transferredAmount = transferTransactionDTO.getAmount();
        if ( transferredAmount > initialBalanceFromAccount) {
            return GenericResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(ErrorMessage.INSUFFICIENT_MONEY)
                    .build();
        }
        fromAccount.get().setCurrentBalance(initialBalanceFromAccount - transferredAmount);
        toAccount.get().setCurrentBalance(initialBalanceToAccount + transferredAmount);

        Date transactionDate = Date.from(Instant.now());
        TransactionModel transactionSentToSave = convertToModel(transferTransactionDTO);
        transactionSentToSave.setDate(transactionDate);
        transactionSentToSave.setTransactionType(TransactionType.SEND_TRANSFER);

        TransactionModel received = convertToModel(transferTransactionDTO);
        received.setAccountModel(toAccount.get());
        received.setDate(transactionDate);
        received.setTransactionType(TransactionType.RECEIVE_TRANSFER);

        saveTransaction(transactionSentToSave);
        saveTransaction(received);
        return GenericResponse.builder()
                .status(HttpStatus.OK)
                .build();
    }

    public Boolean saveTransaction(TransactionModel transactionModel) {
        try{
            transactionRepository.save(transactionModel);
        } catch (Exception exception){
            log.debug(exception.getMessage(), exception);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public TransactionModel convertToModel(TransactionDTO transactionDTO) {
        Optional<AccountModel> account = accountService.getAccount(transactionDTO.getAccountId());

        return account.map(
                acc -> TransactionModel.builder()
                        .accountModel(account.get())
                        .transactionAmount(transactionDTO.getAmount())
                        .build()

        ).orElse(TransactionModel.builder().build());
    }

    public TransactionDTO convertToDTO(TransactionModel transactionModel) {
        return TransactionDTO.builder()
                .amount(transactionModel.getTransactionAmount())
                .id(transactionModel.getId())
                .date(transactionModel.getDate())
                .accountId(transactionModel.getAccountModel().getId())
                .transactionType(transactionModel.getTransactionType())
                .build();
    }

}
