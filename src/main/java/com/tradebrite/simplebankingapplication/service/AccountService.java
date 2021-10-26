package com.tradebrite.simplebankingapplication.service;

import com.tradebrite.simplebankingapplication.DTO.AccountModelDTO;
import com.tradebrite.simplebankingapplication.DTO.AddAccountDTO;
import com.tradebrite.simplebankingapplication.model.AccountModel;
import com.tradebrite.simplebankingapplication.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

//  public AccountModelDTO getBalance()metoda pentru balance.

    public Optional<AccountModel> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public AccountModelDTO addNewAccount(AddAccountDTO addAccountDTO) {
        AccountModel accountModel = convertToModel(addAccountDTO);
        accountModel.setCreateDate(Date.from(Instant.now()));
        accountModel.setCurrentBalance(0.00);
        AccountModel accountSavedInDb = saveAccount(accountModel);

        if (accountSavedInDb == null) {
            return null;
        }

        return convertToDTO(accountSavedInDb);
    }

    public AccountModelDTO getBalance(Long accountId) {
        Optional<AccountModel> accountModel = getAccount(accountId);
        return accountModel.map(
                acc -> convertToDTO(accountModel.get())
        ).orElse(AccountModelDTO.builder().build());

    }

    public AccountModel saveAccount(AccountModel accountModel) {
        AccountModel result = null;

        try{
            result = accountRepository.save(accountModel);
        } catch(Exception exception) {
            log.debug(exception.getMessage(), exception);
        }
        return result;
    }

    public AccountModelDTO convertToDTO(AccountModel accountModel) {
        return AccountModelDTO.builder()
                .createDate(accountModel.getCreateDate())
                .currentBalance(accountModel.getCurrentBalance())
                .customerId(accountModel.getCustomerModel().getId())
                .build();
    }


    public AccountModel convertToModel(AddAccountDTO addAccountDTO) {
        return AccountModel.builder()
                .customerModel(customerService.getCustomer(addAccountDTO.getCustomerId()))
                .build();
    }
}
