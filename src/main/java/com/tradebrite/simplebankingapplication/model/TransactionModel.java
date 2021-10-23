package com.tradebrite.simplebankingapplication.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal transactionAmount;

    @Column
    private TransactionType transactionType;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
    private AccountModel accountModel;


//    TODO after MVP : add currency property;



}
