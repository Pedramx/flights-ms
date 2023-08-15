package aero.smart4aviation.flightsms.service.spec;

import aero.smart4aviation.flightsms.enums.AirportIATACodeEnum;

import java.util.Date;

public interface FlightServiceSpec {

    int numOfDepartingFlights(AirportIATACodeEnum airportIATA, Date date);
    int numOfArrivingFlights(AirportIATACodeEnum airportIATA);
    int piecesOfBaggagesDeparting(AirportIATACodeEnum airportIATA, Date date);
    int piecesOfBaggagesArriving(AirportIATACodeEnum airportIATA);

}
