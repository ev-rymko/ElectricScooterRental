package com.senla.finalProject.dto;

import com.senla.finalProject.enums.ScooterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScooterDto {

    private Long id;
    private ScooterType type;
    private String model;
    private String details;
    private int condition;
    private RentalPointDto rentalPoint;
}
