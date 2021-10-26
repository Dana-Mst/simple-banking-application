package com.tradebrite.simplebankingapplication.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModelDTO {

    private Long id;

    @Size(min=24, max=24)
    private String accountNumber;

    @NotNull
    private Long customerId;

    private Date createDate;

    private Double currentBalance;


    public Boolean isAccountValid(){
        return customerId != null;
    }
}
