package com.svetlicic.filip.cheepflightsmvn.controllers;

import com.amadeus.exceptions.ResponseException;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightFormDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOffersDTO;
import com.svetlicic.filip.cheepflightsmvn.services.FlightOffersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cheep-flights/")
public class FlightOfferController {

    private final FlightOffersService offersService;

    public FlightOfferController(FlightOffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("get/Offers")
    FlightOffersDTO getOffers(@RequestBody FlightFormDTO formDTO) throws ResponseException {
        return offersService.findFlights(formDTO);
    }
}
