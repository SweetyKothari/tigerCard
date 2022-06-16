package com.sahaj.assignment.rule;

import com.sahaj.assignment.dto.ZoneDetail;
import com.sahaj.assignment.util.MockObjectHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeeklyCapedFareTest {

    private FareCalculator fareCalculator=null;
    private static Map<LocalDate,ZoneDetail> dateZoneDetailMap= new HashMap<>();

    @BeforeAll
    public static void initAll(){
        dateZoneDetailMap.putIfAbsent(LocalDate.parse("2021-01-01"),new ZoneDetail(1,2));
        dateZoneDetailMap.putIfAbsent(LocalDate.parse("2021-01-02"),new ZoneDetail(1,2));
        dateZoneDetailMap.putIfAbsent(LocalDate.parse("2021-01-03"),new ZoneDetail(1,2));
    }

    @Test
    public void testWeeklyCapedFareTest(){
        fareCalculator= new WeeklyCapedCalculator();
        double fare=fareCalculator.calculateFare(MockObjectHelper.mockCommuteDetailListWithFare(),null);
        assertEquals(fare,350);
    }

}
