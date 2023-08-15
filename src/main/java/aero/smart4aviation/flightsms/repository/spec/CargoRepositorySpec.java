package aero.smart4aviation.flightsms.repository.spec;

import aero.smart4aviation.flightsms.model.CargoModel;

public interface CargoRepositorySpec {
    CargoModel findByFlightId(int flightId);
}
