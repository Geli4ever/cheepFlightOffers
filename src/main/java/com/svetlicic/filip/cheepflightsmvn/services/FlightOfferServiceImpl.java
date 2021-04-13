package com.svetlicic.filip.cheepflightsmvn.services;

import com.svetlicic.filip.cheepflightsmvn.mappers.FlightOfferMapper;
import com.svetlicic.filip.cheepflightsmvn.model.FlightOffer;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightFormDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOfferDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOffersDTO;
import com.svetlicic.filip.cheepflightsmvn.repositories.FlightOfferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class FlightOfferServiceImpl implements FlightOfferService {

    private final FlightOfferRepository offerRepository;
    private final FlightOfferMapper offerMapper;

    public FlightOfferServiceImpl(FlightOfferRepository offerRepository, FlightOfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    @Override
    public FlightOfferDTO saveFlightOffer(FlightOfferDTO offerDTO) {
        FlightOffer flightOffer = offerMapper.flightOfferDtoToFlightOffer(offerDTO);
        FlightOffer savedOffer = offerRepository.save(flightOffer);
        return offerMapper.flightOfferToFlightOfferDTO(savedOffer);
    }

    @Override
    public FlightOffersDTO findAllOffers(FlightFormDTO formDTO) {
        return new FlightOffersDTO(offerRepository.findAllByDepartureAirportAndArrivalAirportAndNumOfPassengers(
                formDTO.getDepartureAirport(),formDTO.getArrivalAirport(),formDTO.getNumOfPassengers()
               ).stream()
                .filter(offer -> offer.getDeparture().toLocalDate().equals(LocalDate.parse(formDTO.getDeparture()))
                        &&offer.getReturnDate().toLocalDate().equals(LocalDate.parse(formDTO.getReturnDate()))
                    )
                .map(offerMapper::flightOfferToFlightOfferDTO)
                .collect(Collectors.toList()));
    }
}
