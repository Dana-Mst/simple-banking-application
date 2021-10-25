package com.tradebrite.simplebankingapplication.DTO;


import com.tradebrite.simplebankingapplication.model.customer.CustomerModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAccountDTO {

    @NotNull
    private CustomerModel customer;

    private Date createDate;

    private BigDecimal currentBalance;
}
