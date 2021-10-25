package com.tradebrite.simplebankingapplication.model;

import com.tradebrite.simplebankingapplication.model.customer.CustomerModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String accountNumber;

    @Column
    private Date createDate;

    @Column
    @Positive
    private BigDecimal currentBalance;

    @OneToMany(mappedBy = "accountModel")
    private List<TransactionModel> transactions;

//    @ManyToMany(mappedBy = "accounts")
//    private Set<CustomerModel> customers;


    /*
    TODO after the MVP:
     * evaluate option account number as primary key
     * add customerId property and the relation
     * add currency property
    */
}
