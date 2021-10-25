package com.tradebrite.simplebankingapplication.service;

import com.tradebrite.simplebankingapplication.DTO.AddCustomerDTO;
import com.tradebrite.simplebankingapplication.DTO.CustomerDTO;
import com.tradebrite.simplebankingapplication.model.customer.CustomerModel;
import com.tradebrite.simplebankingapplication.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO addNewCustomer(AddCustomerDTO addCustomerDTO) {
        CustomerModel customerModel = convertToModel(addCustomerDTO);
        customerModel.setCreateDate(Date.from(Instant.now()));
        CustomerModel customerSavedInDb = saveCustomer(customerModel);

        if( customerSavedInDb == null) {
            return null;
        }

        return convertToDTO(customerSavedInDb);

    }

    public CustomerModel getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerModel saveCustomer(CustomerModel customerModel) {
        CustomerModel result = null;
        try{
            result = customerRepository.save(customerModel);
        } catch(Exception exception) {
            log.debug(exception.getMessage(), exception);
        }
        return result;
    }

    public CustomerModel convertToModel(AddCustomerDTO addCustomerDTO) {
        return CustomerModel.builder()
                .cnp(addCustomerDTO.getCnp())
                .firstName(addCustomerDTO.getFirstName())
                .lastName(addCustomerDTO.getLastName())
                .phoneNumber(addCustomerDTO.getPhoneNumber())
                .email(addCustomerDTO.getEmail())
                .address(addCustomerDTO.getAddress())
                .build();
    }

    public CustomerDTO convertToDTO(CustomerModel customerModel) {
        return CustomerDTO.builder()
                .cnp(customerModel.getCnp())
                .createDate(customerModel.getCreateDate())
                .id(customerModel.getId())
                .firstName(customerModel.getFirstName())
                .lastName(customerModel.getLastName())
                .email(customerModel.getEmail())
                .phoneNumber(customerModel.getPhoneNumber())
                .address(customerModel.getAddress())
                .accounts(customerModel.getAccounts())
                .build();
    }
}
