package com.svetlicic.filip.cheepflightsmvn.services;

import com.amadeus.exceptions.ResponseException;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightFormDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOffersDTO;

public interface FlightOffersService {
    FlightOffersDTO findFlights(FlightFormDTO flightFormDTO) throws ResponseException;
}
