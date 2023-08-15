package aero.smart4aviation.flightsms.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil{

    public static <T> List<T> concatenateLists(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

}
