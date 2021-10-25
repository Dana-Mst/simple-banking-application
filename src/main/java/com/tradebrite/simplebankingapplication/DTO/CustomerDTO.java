package com.tradebrite.simplebankingapplication.DTO;

import com.tradebrite.simplebankingapplication.model.AccountModel;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;
    @NotNull
    @Digits(fraction = 0, integer = 10)
    @Size(min=13, max = 13)
    private String cnp;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private Date createDate;
    private Set<AccountModel> accounts;
}
