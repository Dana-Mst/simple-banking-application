package com.tradebrite.simplebankingapplication.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModelDTO {

    private String accountNumber;

    private Date createDate;

    private BigDecimal currentBalance;


}
