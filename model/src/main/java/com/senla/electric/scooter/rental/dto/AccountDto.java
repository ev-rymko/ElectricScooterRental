package com.senla.electric.scooter.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    @JsonIgnore
    private String login;
    @JsonIgnore
    private String password;
    private UserDto user;
}
