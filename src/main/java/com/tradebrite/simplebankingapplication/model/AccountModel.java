package com.tradebrite.simplebankingapplication.model;

import lombok.*;
import org.springframework.beans.propertyeditors.CurrencyEditor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Currency;
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

//    TODO for v2: evaluate option account number as primary key

//    TODO add customerId property and the relation

    @Column
    @NotNull
    private String accountNumber;

    @Column
    private Currency currency;

    @Column
    @Positive
    private BigDecimal currentBalance;

    @Transient
    private List<TransactionModel> transactions;





}
