package com.svetlicic.filip.cheepflightsmvn.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightOffersDTO {
    private List<FlightOfferDTO> flightOffers = new ArrayList<>();
}
