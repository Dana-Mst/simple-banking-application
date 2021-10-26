package com.tradebrite.simplebankingapplication.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCustomerDTO {

    @NotNull
    @Size(min=13, max = 13)
    private String cnp;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;

}
