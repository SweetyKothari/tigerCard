package com.test.rule;
import com.test.util.MockObjectHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyCapedFareTest {

    private FareCalculator fareCalculator=null;


    @Test
    public void testTimeBasedFareCal(){
        fareCalculator= new DailyCapedCalculator();
        double fare=fareCalculator.calculateFare(MockObjectHelper.mockCommuteDetailListWithFare(),null);
        assertEquals(fare,325);
    }

    @Test
    public void testTimeBasedFareCalOnChainWeekly(){
        fareCalculator= new DailyCapedCalculator();
        fareCalculator.setNextFareCalculator(new WeeklyCapedCalculator());
        double fare=fareCalculator.calculateFare(MockObjectHelper.mockCommuteDetailListWithFare(),null );
        assertEquals(fare,325);
    }

}