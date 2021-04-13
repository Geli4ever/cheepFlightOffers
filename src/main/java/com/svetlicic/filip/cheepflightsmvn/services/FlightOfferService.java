package com.svetlicic.filip.cheepflightsmvn.services;

import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightFormDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOfferDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOffersDTO;

public interface FlightOfferService {
    FlightOfferDTO saveFlightOffer(FlightOfferDTO offerDTO);
    FlightOffersDTO findAllOffers(FlightFormDTO formDTO);
}
