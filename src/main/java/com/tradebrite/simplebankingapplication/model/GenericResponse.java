package com.tradebrite.simplebankingapplication.model;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {

    private HttpStatus status;
    private String message;
}
