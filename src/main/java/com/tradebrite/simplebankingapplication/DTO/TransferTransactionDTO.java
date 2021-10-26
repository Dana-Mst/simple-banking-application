package com.tradebrite.simplebankingapplication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferTransactionDTO extends TransactionDTO{

    private Long toAccountId;

}
