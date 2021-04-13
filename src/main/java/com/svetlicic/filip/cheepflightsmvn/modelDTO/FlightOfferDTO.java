package com.svetlicic.filip.cheepflightsmvn.modelDTO;

import com.svetlicic.filip.cheepflightsmvn.model.Currency;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightOfferDTO {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departure;
    private LocalDateTime returnDate;
    private int numOfTransfers;
    private int returnNumOfTransfers;
    private int numOfPassengers;
    private BigDecimal flightCost;
    private Currency currency;
}
