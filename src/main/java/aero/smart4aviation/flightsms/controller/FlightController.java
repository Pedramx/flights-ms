package aero.smart4aviation.flightsms.controller;

import aero.smart4aviation.flightsms.enums.AirportIATACodeEnum;
import aero.smart4aviation.flightsms.model.TotalNumberModel;
import aero.smart4aviation.flightsms.service.spec.FlightServiceSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Date;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightServiceSpec flightService;

    @GetMapping("/departing")
    public TotalNumberModel getNumOfDepartingFlights(
            @RequestParam(name = "AirportIATACode")AirportIATACodeEnum airportIATACode,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX") Date date
            ) {
        int totalNumber = flightService.numOfDepartingFlights(airportIATACode, date);
        return new TotalNumberModel(totalNumber);
    }

    @GetMapping("/arriving")
    public TotalNumberModel getNumOfArrivingFlights(
            @RequestParam(name = "AirportIATACode")AirportIATACodeEnum airportIATACode
    ) {
        int totalNumber = flightService.numOfArrivingFlights(airportIATACode);
        return new TotalNumberModel(totalNumber);
    }

    @GetMapping("/departing/pieces")
    public TotalNumberModel getPiecesOfBaggagesDeparting(
            @RequestParam(name = "AirportIATACode")AirportIATACodeEnum airportIATACode,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX") Date date
    ) {
        int totalNumber = flightService.piecesOfBaggagesDeparting(airportIATACode, date);
        return new TotalNumberModel(totalNumber);
    }

    @GetMapping("/arriving/pieces")
    public TotalNumberModel getPiecesOfBaggagesArriving(
            @RequestParam(name = "AirportIATACode")AirportIATACodeEnum airportIATACode
    ) {
        int totalNumber = flightService.piecesOfBaggagesArriving(airportIATACode);
        return new TotalNumberModel(totalNumber);
    }

}
