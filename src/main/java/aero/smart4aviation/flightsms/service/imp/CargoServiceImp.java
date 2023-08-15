package aero.smart4aviation.flightsms.service.imp;

import aero.smart4aviation.flightsms.enums.WeightUnitEnum;
import aero.smart4aviation.flightsms.model.CargoModel;
import aero.smart4aviation.flightsms.model.CargoPieceModel;
import aero.smart4aviation.flightsms.model.FlightModel;
import aero.smart4aviation.flightsms.model.TotalWeightModel;
import aero.smart4aviation.flightsms.repository.spec.CargoRepositorySpec;
import aero.smart4aviation.flightsms.repository.spec.FlightRepositorySpec;
import aero.smart4aviation.flightsms.service.spec.CargoServiceSpec;
import aero.smart4aviation.flightsms.util.WeightUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;
import java.util.function.ToIntFunction;

@Service
public class CargoServiceImp implements CargoServiceSpec {

    @Autowired
    private CargoRepositorySpec cargoRepository;
    @Autowired
    private FlightRepositorySpec flightRepository;
    private static final Logger logger = LoggerFactory.getLogger(CargoServiceImp.class);

    @Override
    public List<CargoPieceModel> getCargoWeight(int flightNumber, Date date) {
        FlightModel flight = this.findFlightOrThrowException(flightNumber, date);
        CargoModel cargoObject = cargoRepository.findByFlightId(flight.getFlightId());
        return cargoObject.getCargo();
    }

    @Override
    public List<CargoPieceModel> getBaggageWeight(int flightNumber, Date date) {
        FlightModel flight = this.findFlightOrThrowException(flightNumber, date);
        CargoModel cargoObject = cargoRepository.findByFlightId(flight.getFlightId());
        return cargoObject.getBaggage();
    }

    @Override
    public TotalWeightModel getTotalWeight(int flightNumber, Date date, WeightUnitEnum weightUnit) {
        FlightModel flight = this.findFlightOrThrowException(flightNumber, date);
        CargoModel cargoObject = cargoRepository.findByFlightId(flight.getFlightId());
        ToIntFunction<? super CargoPieceModel> mapper = WeightUtil.lbToKgMapper;
        if(weightUnit.equals(WeightUnitEnum.lb))
            mapper = WeightUtil.kgToLbMapper;
        int cargoSum = cargoObject.getCargo().stream().mapToInt(mapper).sum();
        int baggageSum = cargoObject.getBaggage().stream().mapToInt(mapper).sum();
        int totalWeight = cargoSum + baggageSum;
        return TotalWeightModel
                .builder()
                .flightId(flight.getFlightId())
                .totalWeight(totalWeight)
                .weightUnit(weightUnit)
                .build();
    }

    private FlightModel findFlightOrThrowException(int flightNumber, Date date) {
        FlightModel flight = flightRepository.findByFlightNumberAndDate(flightNumber, date);
        if(flight == null) {
            String errorMessage = "couldn't find flight with flightNumber: " + flightNumber;
            logger.error(errorMessage);
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, errorMessage);
        }
        return flight;
    }

}
