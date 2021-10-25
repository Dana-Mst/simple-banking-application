package com.tradebrite.simplebankingapplication.service;

import com.tradebrite.simplebankingapplication.DTO.AccountModelDTO;
import com.tradebrite.simplebankingapplication.model.AccountModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;

@Service
@Slf4j
public class AccountService {

/*    public AccountModelDTO addNewAccount(AccountModelDTO accountModelDTO) {
        AccountModel accountModel = convertToModel(accountModelDTO);
        accountModel.setCreateDate(Date.from(Instant.now()));
        accountModel.setCurrentBalance(BigDecimal.ZERO);

    }*/

    public AccountModelDTO convertToDTO(AccountModel accountModel) {
        return AccountModelDTO.builder()
                .accountNumber(accountModel.getAccountNumber())
                .createDate(accountModel.getCreateDate())
                .currentBalance(accountModel.getCurrentBalance())
                .build();
    }

    public AccountModel convertToModel(AccountModelDTO accountModelDTO) {
        return AccountModel.builder()
                .accountNumber(accountModelDTO.getAccountNumber())
                .build();
    }
}
