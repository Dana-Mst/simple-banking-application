package com.tradebrite.simplebankingapplication.model;

import com.tradebrite.simplebankingapplication.model.customer.CustomerModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private String accountNumber;

    @Column
    private Date createDate;

    @Column
    private Double currentBalance;

    @OneToMany(mappedBy = "accountModel")
    private List<TransactionModel> transactions;

    @ManyToOne
    @JoinColumn(name = "customerModel_id")
    private CustomerModel customerModel;


    /*
    TODO after the MVP:
     * evaluate option account number as primary key
     * add customerId property and the relation
     * add currency property
    */
}
