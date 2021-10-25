package com.tradebrite.simplebankingapplication.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAccountDTO {

    @NotNull
    private Long customerId;

}
