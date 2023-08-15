package aero.smart4aviation.flightsms.service.spec;

import aero.smart4aviation.flightsms.enums.WeightUnitEnum;
import aero.smart4aviation.flightsms.model.CargoPieceModel;
import aero.smart4aviation.flightsms.model.TotalWeightModel;

import java.util.Date;
import java.util.List;

public interface CargoServiceSpec {

    List<CargoPieceModel> getCargoWeight(int flightNumber, Date date);
    List<CargoPieceModel> getBaggageWeight(int flightNumber, Date date);
    TotalWeightModel getTotalWeight(int flightNumber, Date date, WeightUnitEnum weightUnit);

}
