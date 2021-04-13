package com.svetlicic.filip.cheepflightsmvn.mappers;

import com.svetlicic.filip.cheepflightsmvn.model.FlightOffer;
import com.svetlicic.filip.cheepflightsmvn.modelDTO.FlightOfferDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightOfferMapper {

    FlightOfferMapper INSTANCE = Mappers.getMapper(FlightOfferMapper.class);

    FlightOfferDTO flightOfferToFlightOfferDTO(FlightOffer offer);

    @Mapping(target = "id", ignore = true)
    FlightOffer flightOfferDtoToFlightOffer(FlightOfferDTO offerDTO);
}
