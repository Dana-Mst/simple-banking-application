package com.tradebrite.simplebankingapplication.controller;

import com.tradebrite.simplebankingapplication.DTO.AccountModelDTO;
import com.tradebrite.simplebankingapplication.DTO.AddAccountDTO;
import com.tradebrite.simplebankingapplication.model.errors.ErrorMessage;
import com.tradebrite.simplebankingapplication.service.AccountService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/addNewAccount")
    public ResponseEntity addNewAccount(@RequestBody @Valid AddAccountDTO addAccountDTO){
        AccountModelDTO result = accountService.addNewAccount(addAccountDTO);

        if(  result == null){
            return new ResponseEntity(ErrorMessage.ERROR_WHEN_SAVING_IN_DB, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);
    }

}
