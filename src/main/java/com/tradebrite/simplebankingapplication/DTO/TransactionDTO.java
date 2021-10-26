package com.tradebrite.simplebankingapplication.DTO;

import com.tradebrite.simplebankingapplication.model.TransactionType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    private Long id;
    @NotNull
    private Double amount;
    @NotNull
    private Long accountId;

    private Date date;

    private TransactionType transactionType;







}
