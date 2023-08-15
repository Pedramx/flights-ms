package aero.smart4aviation.flightsms.repository.spec;

import aero.smart4aviation.flightsms.enums.AirportIATACodeEnum;
import aero.smart4aviation.flightsms.model.FlightModel;

import java.util.Date;
import java.util.List;

public interface FlightRepositorySpec {
    FlightModel findByFlightNumberAndDate(int flightNumber, Date date);
    List<FlightModel> findByDepartureAirportIATA(AirportIATACodeEnum airportIATA);
    List<FlightModel> findByDepartureAirportIATAAndDate(AirportIATACodeEnum airportIATA, Date date);
    List<FlightModel> findByArrivalAirportIATA(AirportIATACodeEnum airportIATA);
}
