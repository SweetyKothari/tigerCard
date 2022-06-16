package com.sahaj.assignment.config;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/*
  This class maintains all configuration about peak timing,daily/weekly/monthly caped limit
  This ideally should be moved to database or properties file which will not involve code change
  @author Sweety Kothari
 */
public class FareConfiguration {
    private static final Map<Integer, Map<Integer, Double>> dailyCapFareData = new HashMap<>();
    private static final Map<DayOfWeek, Map<LocalTime, LocalTime>> peakHrFareData = new HashMap<>();
    private static final Map<Integer, Map<Integer, Double>> zonePeakHrFareData = new HashMap<>();
    private static final Map<Integer, Map<Integer, Double>> zoneNonPeakHrFareData = new HashMap<>();
    private static final Map<Integer, Map<Integer, Double>> zoneWeekFareData = new HashMap<>();

    static
    {
        fillTimeFare();
        fillDailyCapFare();
        fillZonePeakFare();
        fillZoneNonPeakFare();
        fillZoneWeekFare();
    }


    private static void fillDailyCapFare()
    {
        dailyCapFareData.put(1, new HashMap<Integer, Double>() {
                private static final long serialVersionUID = 1L;
                {
                    put(1, 100.0);
                    put(2, 120.0);
                }
        });
        dailyCapFareData.put(2, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 120.0);
                put(2, 80.0);
            }
        });
    }

    private static void fillZonePeakFare()
    {
        zonePeakHrFareData.put(1, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 30.0);
                put(2, 35.0);
            }
        });
        zonePeakHrFareData.put(2, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 35.0);
                put(2, 25.0);
            }
        });
    }

    private static void fillZoneNonPeakFare()
    {
        zoneNonPeakHrFareData.put(1, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 25.0);
                put(2, 30.0);
            }
        });
        zoneNonPeakHrFareData.put(2, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 30.0);
                put(2, 20.0);
            }
        });
    }

    private static void fillTimeFare()
    {
        var weekDayPeakTime = new HashMap<LocalTime, LocalTime>();
        weekDayPeakTime.put(LocalTime.of(7, 0, 0), LocalTime.of(10, 30, 0));
        weekDayPeakTime.put(LocalTime.of(17, 0, 0), LocalTime.of(20, 0, 0));

        var weekendPeakTime = new HashMap<LocalTime, LocalTime>();
        weekendPeakTime.put(LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0));
        weekendPeakTime.put(LocalTime.of(18, 0, 0), LocalTime.of(22, 0, 0));

        peakHrFareData.put(DayOfWeek.MONDAY, weekDayPeakTime);
        peakHrFareData.put(DayOfWeek.TUESDAY, weekDayPeakTime);
        peakHrFareData.put(DayOfWeek.WEDNESDAY, weekDayPeakTime);
        peakHrFareData.put(DayOfWeek.THURSDAY, weekDayPeakTime);
        peakHrFareData.put(DayOfWeek.FRIDAY, weekDayPeakTime);
        peakHrFareData.put(DayOfWeek.SATURDAY, weekendPeakTime);
        peakHrFareData.put(DayOfWeek.SUNDAY, weekendPeakTime);
    }

    private static void fillZoneWeekFare()
    {
        zoneWeekFareData.put(1, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 500.0);
                put(2, 600.0);
            }
        });
        zoneWeekFareData.put(2, new HashMap<Integer, Double>() {
            private static final long serialVersionUID = 1L;
            {
                put(1, 600.0);
                put(2, 400.0);
            }
        });
    }

    public static Map<Integer, Map<Integer, Double>> getDailyCapFareData() {
        return dailyCapFareData;
    }

    public static Map<DayOfWeek, Map<LocalTime, LocalTime>> getPeakHrFareData() {
        return peakHrFareData;
    }

    public static Map<Integer, Map<Integer, Double>> getZonePeakHrFareData() {
        return zonePeakHrFareData;
    }

    public static Map<Integer, Map<Integer, Double>> getZoneNonPeakHrFareData() {
        return zoneNonPeakHrFareData;
    }

    public static Map<Integer, Map<Integer, Double>> getZoneWeekFareData() {
        return zoneWeekFareData;
    }
}
