package com.svetlicic.filip.cheepflightsmvn.repositories;

import com.svetlicic.filip.cheepflightsmvn.model.FlightOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightOfferRepository extends JpaRepository<FlightOffer, Long> {
    List<FlightOffer> findAllByDepartureAirportAndArrivalAirportAndNumOfPassengers(
            String departureAirport, String arrivalAirport, int numOfPassengers);
}
