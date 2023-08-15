package aero.smart4aviation.flightsms.controller;

import aero.smart4aviation.flightsms.enums.WeightUnitEnum;
import aero.smart4aviation.flightsms.model.CargoPieceModel;
import aero.smart4aviation.flightsms.model.TotalWeightModel;
import aero.smart4aviation.flightsms.service.spec.CargoServiceSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoServiceSpec cargoService;

    @GetMapping("/weight")
    public List<CargoPieceModel> GetCargoWeight(
            @RequestParam(name = "flightNumber") int flightNumber,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX") Date date
    ) {
        return cargoService.getCargoWeight(flightNumber, date);
    }

    @GetMapping("/weight/baggage")
    public List<CargoPieceModel> GetBaggageWeight(
            @RequestParam(name = "flightNumber") int flightNumber,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX") Date date
    ) {
        return cargoService.getBaggageWeight(flightNumber, date);
    }

    @GetMapping("/weight/total")
    public TotalWeightModel GetTotalWeight(
            @RequestParam(name = "flightNumber") int flightNumber,
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX") Date date,
            @RequestParam(name = "weightUnit") WeightUnitEnum weightUnit
            ) {
        return cargoService.getTotalWeight(flightNumber, date, weightUnit);
    }

}
