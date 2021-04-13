package com.svetlicic.filip.cheepflightsmvn.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class FlightOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departure;
    private LocalDateTime returnDate;
    private int numOfTransfers;
    private int returnNumOfTransfers;
    private int numOfPassengers;
    private BigDecimal flightCost;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
