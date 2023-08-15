package aero.smart4aviation.flightsms.repository.imp;

import aero.smart4aviation.flightsms.enums.AirportIATACodeEnum;
import aero.smart4aviation.flightsms.model.FlightModel;
import aero.smart4aviation.flightsms.repository.spec.FlightRepositorySpec;
import aero.smart4aviation.flightsms.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImp implements FlightRepositorySpec {

    private static List<FlightModel> db;
    private static final Logger logger = LoggerFactory.getLogger(FlightRepositoryImp.class);

    public FlightRepositoryImp() {
        try {
            Resource resource = new ClassPathResource("static/data/flight.json");
            InputStream inputStream = resource.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            objectMapper.setDateFormat(dateFormat);
            FlightModel[] flights = objectMapper.readValue(inputStream, FlightModel[].class);
            FlightRepositoryImp.db = Arrays.asList(flights);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public FlightModel findByFlightNumberAndDate(int flightNumber, Date date) {
        return db
                .stream()
                .filter(
                        flight -> flight.getFlightNumber() == flightNumber
                                && DateUtil.isSameDay(flight.getDepartureDate(), date)
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<FlightModel> findByDepartureAirportIATA(AirportIATACodeEnum airportIATA) {
        return db
                .stream()
                .filter(
                        flight -> flight.getDepartureAirportIATACode().equals(airportIATA)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightModel> findByDepartureAirportIATAAndDate(AirportIATACodeEnum airportIATA, Date date) {
        return db
                .stream()
                .filter(
                        flight -> flight.getDepartureAirportIATACode().equals(airportIATA)
                                && DateUtil.isSameDay(flight.getDepartureDate(), date)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightModel> findByArrivalAirportIATA(AirportIATACodeEnum airportIATA) {
        return db
                .stream()
                .filter(
                        flight -> flight.getArrivalAirportIATACode().equals(airportIATA)
                )
                .collect(Collectors.toList());
    }
}
