package com.test.rule;
import com.test.util.MockObjectHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeCapedFareTest {

    private FareCalculator fareCalculator=null;

    @Test
    public void testTimeBasedFareCal(){
        fareCalculator= new TimeCapedCalculator();
        double fare=fareCalculator.calculateFare(MockObjectHelper.mockCommuteDetailList(),null);
        assertEquals(fare,360);
    }

    @Test
    public void testTimeBasedFareCalOnChainDaily(){
        fareCalculator= new TimeCapedCalculator();
        fareCalculator.setNextFareCalculator(new DailyCapedCalculator());
        double fare=fareCalculator.calculateFare(MockObjectHelper.mockCommuteDetailList(),null);
        assertEquals(fare,330);
    }

    @Test
    public void testTimeBasedFareCalOnChainWeekly(){
        fareCalculator= new TimeCapedCalculator();
        DailyCapedCalculator dailyCapedCalculator= new DailyCapedCalculator();
        dailyCapedCalculator.setNextFareCalculator(new WeeklyCapedCalculator());
        fareCalculator= new TimeCapedCalculator();
        fareCalculator.setNextFareCalculator(dailyCapedCalculator);
        double fare=fareCalculator.calculateFare(MockObjectHelper.mockCommuteDetailList(),null);
        assertEquals(fare,330);
    }
}