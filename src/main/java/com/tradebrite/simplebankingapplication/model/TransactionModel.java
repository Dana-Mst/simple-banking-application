package com.tradebrite.simplebankingapplication.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double transactionAmount;

    @Column
    private TransactionType transactionType;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private AccountModel accountModel;

    @Transient
    private AccountModel toAccount;

}
