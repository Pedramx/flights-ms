package aero.smart4aviation.flightsms.model;

import aero.smart4aviation.flightsms.enums.AirportIATACodeEnum;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightModel {

    private int flightId;
    private int flightNumber;
    private AirportIATACodeEnum departureAirportIATACode;
    private AirportIATACodeEnum arrivalAirportIATACode;
    private Date departureDate;

}
