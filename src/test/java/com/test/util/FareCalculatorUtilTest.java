package com.test.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FareCalculatorUtilTest {

    @Test
    public void testPeakTimeForWeekend(){
       assertTrue(FareCalculatorUtil.isPeakTime(LocalDate.of(2022,06,12), LocalTime.of(10,30)));
    }
    @Test
    public void testNonPeakTimeForWeekend(){
        assertFalse(FareCalculatorUtil.isPeakTime(LocalDate.of(2022,06,12), LocalTime.of(17,30)));
    }
    @Test
    public void testPeakTimeForWeekDay(){
        assertTrue(FareCalculatorUtil.isPeakTime(LocalDate.of(2022,06,13), LocalTime.of(10,30)));
    }
    @Test
    public void testNonPeakTimeForWeekDay(){
        assertFalse(FareCalculatorUtil.isPeakTime(LocalDate.of(2022,06,13), LocalTime.of(21,30)));
    }

    @Test
    public void testWeekNo(){
        int weekNo=FareCalculatorUtil.getWeekNo(LocalDate.of(2022,06,13));
        assertEquals(24,weekNo);
    }

    @Test
    public void testComputeFartherMap(){
        Map map=FareCalculatorUtil.computeFarthestZonePerDate(MockObjectHelper.mockCommuteDetailList());
        assertEquals(map.size(),3);
    }
}
