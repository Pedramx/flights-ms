package aero.smart4aviation.flightsms.util;

import aero.smart4aviation.flightsms.enums.WeightUnitEnum;
import aero.smart4aviation.flightsms.model.CargoPieceModel;

import java.util.function.ToIntFunction;

public class WeightUtil {
    private static final double LB_TO_KG = 0.45359237;
    private static final double KG_TO_LB = 2.20462262185;

    public static int convert(int value, WeightUnitEnum fromUnit, WeightUnitEnum toUnit) {
        if (fromUnit == WeightUnitEnum.lb && toUnit == WeightUnitEnum.kg) {
            return (int) (value * LB_TO_KG);
        } else if (fromUnit == WeightUnitEnum.kg && toUnit == WeightUnitEnum.lb) {
            return (int) (value * KG_TO_LB);
        } else {
            throw new IllegalArgumentException("Unsupported conversion");
        }
    }

    public static final ToIntFunction<? super CargoPieceModel> lbToKgMapper = cargo -> {
        if(cargo.getWeightUnit().equals(WeightUnitEnum.lb))
            return WeightUtil.convert(cargo.getWeight(), WeightUnitEnum.lb, WeightUnitEnum.kg);
        return cargo.getWeight();
    };

    public static final ToIntFunction<? super CargoPieceModel> kgToLbMapper = cargo -> {
        if(cargo.getWeightUnit().equals(WeightUnitEnum.kg))
            return WeightUtil.convert(cargo.getWeight(), WeightUnitEnum.kg, WeightUnitEnum.lb);
        return cargo.getWeight();
    };

}
