package com.tradebrite.simplebankingapplication.DTO;

import lombok.*;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModelDTO {

    @Size(min=24, max=24)
    private String accountNumber;

    private Date createDate;

    private BigDecimal currentBalance;


}
