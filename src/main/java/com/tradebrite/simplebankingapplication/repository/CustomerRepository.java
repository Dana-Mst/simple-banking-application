package com.tradebrite.simplebankingapplication.repository;

import com.tradebrite.simplebankingapplication.model.customer.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {


}
