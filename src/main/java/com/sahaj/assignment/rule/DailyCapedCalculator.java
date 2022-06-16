package com.sahaj.assignment.rule;

import com.sahaj.assignment.config.FareConfiguration;
import com.sahaj.assignment.dto.CommuteDetailDTO;
import com.sahaj.assignment.dto.ZoneDetail;

import java.time.LocalDate;
import java.util.*;

import com.sahaj.assignment.util.FareCalculatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class helps to check dailycaped limit & assign correct fare to each commute
 */
public class DailyCapedCalculator extends FareCalculator {

    private FareCalculator fareCalculator;

    static Logger logger = LoggerFactory.getLogger(DailyCapedCalculator.class);


    @Override
    public double calculateFare(List<CommuteDetailDTO> commuteDetailDTOS ,
                                Map<LocalDate,ZoneDetail> dateZoneFarthestMap){
        Map restDayFareMap = new HashMap<LocalDate, Double>();

        if(dateZoneFarthestMap ==null )
            dateZoneFarthestMap = FareCalculatorUtil.computeFarthestZonePerDate(commuteDetailDTOS);
        double sum=0;

        for(CommuteDetailDTO commuteDetailDTO:commuteDetailDTOS) {
           ZoneDetail farthestZone =dateZoneFarthestMap.get(commuteDetailDTO.getDate());
            double dailyCapedFareForZone = FareConfiguration.
                    getDailyCapFareData().get(farthestZone.getFromZone()).
                    get(farthestZone.getToZone());
            double balanceFare= (double) restDayFareMap.getOrDefault(commuteDetailDTO.getDate() ,
                    dailyCapedFareForZone);
            if(commuteDetailDTO.getFare() > balanceFare){
                restDayFareMap.put(commuteDetailDTO.getDate(),0.0);
                commuteDetailDTO.setFare(balanceFare);
            }else{
                double remainingAmt=balanceFare-commuteDetailDTO.getFare() < 0 ? 0 :balanceFare-commuteDetailDTO.getFare();
                restDayFareMap.put(commuteDetailDTO.getDate(),remainingAmt);
                commuteDetailDTO.setFare(commuteDetailDTO.getFare());
            }
            sum+=commuteDetailDTO.getFare();
        }
        logger.debug("Fare Computation After Running Daily Caped Rules ");
        FareCalculatorUtil.printFareDetail(Collections.unmodifiableList(commuteDetailDTOS));
        return fareCalculator!=null ?fareCalculator.calculateFare(commuteDetailDTOS,dateZoneFarthestMap):sum;

    }



    @Override
    public void setNextFareCalculator(FareCalculator fareCalculator) {
        this.fareCalculator=fareCalculator;
    }


}


