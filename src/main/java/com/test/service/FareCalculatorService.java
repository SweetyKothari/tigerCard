package com.test.service;

import com.test.dto.CommuteDetailDTO;
import com.test.rule.DailyCapedCalculator;
import com.test.rule.FareCalculator;
import com.test.rule.TimeCapedCalculator;
import com.test.rule.WeeklyCapedCalculator;

import java.util.List;

/**
 * Service class to pass commuteDetail & calculate fare
 */
public class FareCalculatorService {

    private static FareCalculator fareCalculatorChain = null;

    static {
        createChain();
    }

    public double calculate(List<CommuteDetailDTO> commuteDetailDTOList){
        return fareCalculatorChain.calculateFare(commuteDetailDTOList,null);
    }

    public static FareCalculator createChain(){
        if(fareCalculatorChain ==null) {
            FareCalculator timeCapedCalculator = new TimeCapedCalculator();
            FareCalculator dailyCapedCalculator = new DailyCapedCalculator();
            FareCalculator weeklyCapedCalculator = new WeeklyCapedCalculator();
            dailyCapedCalculator.setNextFareCalculator(weeklyCapedCalculator);
            timeCapedCalculator.setNextFareCalculator(dailyCapedCalculator);
            fareCalculatorChain = timeCapedCalculator;
        }
        return fareCalculatorChain;
    }
}
