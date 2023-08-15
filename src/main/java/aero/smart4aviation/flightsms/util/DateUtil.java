package aero.smart4aviation.flightsms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dayFormat.format(date1).equals(dayFormat.format(date2));
    }

}
