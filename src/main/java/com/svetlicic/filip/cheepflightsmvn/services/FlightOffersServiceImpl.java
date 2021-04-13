package com.svetlicic.filip.cheepflightsmvn.services;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.svetlicic.filip.cheepflightsmvn.model.AmadeusApi;
import com.svetlicic.filip.cheepflightsmvn.model.Currency;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightFormDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOfferDTO;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOffersDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FlightOffersServiceImpl implements FlightOffersService{

    private final FlightOfferService offerService;

    public FlightOffersServiceImpl(FlightOfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public FlightOffersDTO findFlights(FlightFormDTO flightFormDTO) throws ResponseException {

        FlightOffersDTO offersDTO = offerService.findAllOffers(flightFormDTO);

        if(offersDTO.getFlightOffers().size() == 0){
            System.out.println("unutra");
            FlightOfferSearch[] flightOffersSearches = AmadeusApi.getInstance().getAmadeus().shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", flightFormDTO.getDepartureAirport() )
                            .and("destinationLocationCode", flightFormDTO.getArrivalAirport())
                            .and("departureDate", flightFormDTO.getDeparture())
                            .and("returnDate", flightFormDTO.getReturnDate())
                            .and("adults", flightFormDTO.getNumOfPassengers()));

            for(FlightOfferSearch offerSearch:flightOffersSearches){
                FlightOfferSearch.Itinerary[] itineraries = offerSearch.getItineraries();
                FlightOfferSearch.SearchSegment[] segments = itineraries[0].getSegments();
                FlightOfferSearch.AirportInfo airportInfo = segments[0].getDeparture();
                String airport = airportInfo.getIataCode();
                String departure = airportInfo.getAt();
                FlightOfferSearch.SearchSegment[] segments1 = itineraries[1].getSegments();
                FlightOfferSearch.AirportInfo returnAirportInfo = segments1[0].getDeparture();
                String returnAirport = returnAirportInfo.getIataCode();
                String returnDeparture = returnAirportInfo.getAt();

                double totalPrice = 0;
                for(int i = 0; i<offerSearch.getTravelerPricings().length; i++){
                    totalPrice+=offerSearch.getTravelerPricings()[i].getPrice().getTotal();
                }

                FlightOfferDTO offerDTO = FlightOfferDTO.builder()
                        .departureAirport(airport)
                        .arrivalAirport(returnAirport)
                        .departure(LocalDateTime.parse(departure))
                        .returnDate(LocalDateTime.parse(returnDeparture))
                        .numOfTransfers(itineraries[0].getSegments().length-1)
                        .returnNumOfTransfers(itineraries[1].getSegments().length-1)
                        .numOfPassengers(offerSearch.getTravelerPricings().length)
                        .flightCost(BigDecimal.valueOf(totalPrice))
                        .currency(Currency.valueOf(Currency.class, "EUR"))
                        .build();
                FlightOfferDTO savedFlightOffer = offerService.saveFlightOffer(offerDTO);
                savedFlightOffer.setFlightCost(currencyCalculation(flightFormDTO.getCurrency(), savedFlightOffer.getFlightCost()));
                savedFlightOffer.setCurrency(Currency.valueOf(Currency.class, flightFormDTO.getCurrency()));
                offersDTO.getFlightOffers().add(savedFlightOffer);
            }
        } else {
            System.out.println("ovdje");
            for(FlightOfferDTO offer:offersDTO.getFlightOffers()){
                offer.setFlightCost(currencyCalculation(flightFormDTO.getCurrency(), offer.getFlightCost()));
                offer.setCurrency(Currency.valueOf(Currency.class, flightFormDTO.getCurrency()));
            }
        }
        return offersDTO;
    }

    BigDecimal currencyCalculation(String currency, BigDecimal totalPrice){
        switch (currency){
            case "HRK": return totalPrice.multiply(BigDecimal.valueOf(7.50));
            case "USD": return totalPrice.multiply(BigDecimal.valueOf(1.19));
            default: return totalPrice;
        }
    }
}
