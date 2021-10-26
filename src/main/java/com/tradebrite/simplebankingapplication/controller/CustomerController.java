package com.tradebrite.simplebankingapplication.controller;


import com.tradebrite.simplebankingapplication.DTO.AddCustomerDTO;
import com.tradebrite.simplebankingapplication.DTO.CustomerDTO;
import com.tradebrite.simplebankingapplication.model.errors.ErrorMessage;
import com.tradebrite.simplebankingapplication.service.CustomerService;
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
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addNewCustomer")
    public ResponseEntity addNewCustomer(@RequestBody @Valid AddCustomerDTO addCustomerDTO) {
        CustomerDTO result = customerService.addNewCustomer(addCustomerDTO );
//        TODO check if CNP already exists in clients DB
        if(  result == null){
            return new ResponseEntity(ErrorMessage.ERROR_WHEN_SAVING_IN_DB, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);
    }



}
