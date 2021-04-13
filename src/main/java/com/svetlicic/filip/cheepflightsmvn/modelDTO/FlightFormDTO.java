package com.svetlicic.filip.cheepflightsmvn.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightFormDTO {
    private String departureAirport;
    private String arrivalAirport;
    private String departure;
    private String returnDate;
    private int numOfPassengers;
    private String currency;
}
