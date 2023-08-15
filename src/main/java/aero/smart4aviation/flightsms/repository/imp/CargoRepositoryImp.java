package aero.smart4aviation.flightsms.repository.imp;

import aero.smart4aviation.flightsms.model.CargoModel;
import aero.smart4aviation.flightsms.repository.spec.CargoRepositorySpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Repository
public class CargoRepositoryImp implements CargoRepositorySpec {

    private static List<CargoModel> db;
    private static final Logger logger = LoggerFactory.getLogger(CargoRepositoryImp.class);

    public CargoRepositoryImp() {
        try {
            Resource resource = new ClassPathResource("static/data/cargo.json");
            InputStream inputStream = resource.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            CargoModel[] cargos = objectMapper.readValue(inputStream, CargoModel[].class);
            CargoRepositoryImp.db = Arrays.asList(cargos);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public CargoModel findByFlightId(int flightId) {
        return db
                .stream()
                .filter(cargo -> cargo.getFlightId() == flightId)
                .findFirst()
                .orElse(null);
    }
}
