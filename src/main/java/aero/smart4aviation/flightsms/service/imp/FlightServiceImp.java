package aero.smart4aviation.flightsms.service.imp;

import aero.smart4aviation.flightsms.enums.AirportIATACodeEnum;
import aero.smart4aviation.flightsms.model.CargoModel;
import aero.smart4aviation.flightsms.model.CargoPieceModel;
import aero.smart4aviation.flightsms.model.FlightModel;
import aero.smart4aviation.flightsms.repository.spec.CargoRepositorySpec;
import aero.smart4aviation.flightsms.repository.spec.FlightRepositorySpec;
import aero.smart4aviation.flightsms.service.spec.FlightServiceSpec;
import aero.smart4aviation.flightsms.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImp implements FlightServiceSpec {

    @Autowired
    private CargoRepositorySpec cargoRepository;
    @Autowired
    private FlightRepositorySpec flightRepository;

    @Override
    public int numOfDepartingFlights(AirportIATACodeEnum airportIATA, Date date) {
        List<FlightModel> flights;
        if(date != null) {
            flights = flightRepository.findByDepartureAirportIATAAndDate(airportIATA, date);
        } else {
            flights = flightRepository.findByDepartureAirportIATA(airportIATA);
        }
        return flights.size();
    }

    @Override
    public int numOfArrivingFlights(AirportIATACodeEnum airportIATA) {
        List<FlightModel> flights;
        flights = flightRepository.findByArrivalAirportIATA(airportIATA);
        return flights.size();
    }

    @Override
    public int piecesOfBaggagesDeparting(AirportIATACodeEnum airportIATA, Date date) {
        List<FlightModel> flights;
        if(date != null) {
            flights = flightRepository.findByDepartureAirportIATAAndDate(airportIATA, date);
        } else {
            flights = flightRepository.findByDepartureAirportIATA(airportIATA);
        }
        return this.getTotalPieces(flights);
    }

    @Override
    public int piecesOfBaggagesArriving(AirportIATACodeEnum airportIATA) {
        List<FlightModel> flights;
        flights = flightRepository.findByArrivalAirportIATA(airportIATA);
        return this.getTotalPieces(flights);
    }

    private int getTotalPieces(List<FlightModel> flights) {
        List<CargoModel> cargoObjects = flights
                .stream()
                .map(flight -> cargoRepository.findByFlightId(flight.getFlightId()))
                .collect(Collectors.toList());
        List<CargoPieceModel> cargoPieces = cargoObjects
                .stream()
                .map(CargoModel::getBaggage)
                .reduce(new ArrayList<>(), ListUtil::concatenateLists);
        int totalPieces = cargoPieces
                .stream()
                .mapToInt(CargoPieceModel::getPieces)
                .sum();
        return totalPieces;
    }
}
