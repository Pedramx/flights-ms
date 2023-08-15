package aero.smart4aviation.flightsms.model;

import aero.smart4aviation.flightsms.enums.WeightUnitEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalWeightModel {

    private WeightUnitEnum weightUnit;
    private int flightId;
    private int totalWeight;

}
