package com.sahaj.assignment.service;

import com.sahaj.assignment.dto.CommuteDetailDTO;
import com.sahaj.assignment.rule.*;

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
