package com.sahaj.assignment.config;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FareConfigurationTest {

    @Test
    public void testZoneWeekFareData(){
        assertTrue(FareConfiguration.getZoneWeekFareData().size() ==2);
        assertTrue(FareConfiguration.getZoneWeekFareData().keySet().contains(1));
        assertTrue(FareConfiguration.getZoneWeekFareData().keySet().contains(2));

    }
    @Test
    public void testDailyCapedFareData(){
        assertTrue(FareConfiguration.getDailyCapFareData().size() ==2);
        assertTrue(FareConfiguration.getDailyCapFareData().keySet().contains(1));
        assertTrue(FareConfiguration.getDailyCapFareData().keySet().contains(2));
    }
    @Test
    public void testPeakHrData(){
        assertTrue(FareConfiguration.getPeakHrFareData().size() ==7);
    }

    @Test
    public void testZoneNonPeakHrData(){
        assertTrue(FareConfiguration.getZoneNonPeakHrFareData().size() ==2);
        assertTrue(FareConfiguration.getZoneNonPeakHrFareData().keySet().contains(1));
        assertTrue(FareConfiguration.getZoneNonPeakHrFareData().keySet().contains(2));
    }

}
