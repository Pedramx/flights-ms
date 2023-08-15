package aero.smart4aviation.flightsms.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoModel {

    private int flightId;
    private List<CargoPieceModel> baggage;
    private List<CargoPieceModel> cargo;

}
