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
public class UserDataDto {

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
}
