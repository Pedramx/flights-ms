package aero.smart4aviation.flightsms.model;

import aero.smart4aviation.flightsms.enums.WeightUnitEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoPieceModel {

    private long id;
    private int weight;
    private WeightUnitEnum weightUnit;
    private int pieces;

}
