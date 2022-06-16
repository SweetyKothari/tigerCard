package com.sahaj.assignment.rule;

import com.sahaj.assignment.config.FareConfiguration;
import com.sahaj.assignment.dto.CommuteDetailDTO;
import com.sahaj.assignment.dto.ZoneDetail;
import com.sahaj.assignment.util.FareCalculatorUtil;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sahaj.assignment.util.FareCalculatorUtil.printFareDetail;

/**
 * This class helps to check weekly caped limit & assign correct fare to each commute
 */
public class WeeklyCapedCalculator extends FareCalculator {

    static Logger logger = LoggerFactory.getLogger(WeeklyCapedCalculator.class);

    private FareCalculator fareCalculator;

    @Override
    public void setNextFareCalculator(FareCalculator fareCalculator) {
        this.fareCalculator=fareCalculator;

    }

    public double calculateFare(List<CommuteDetailDTO> commuteDetailDTOS, Map<LocalDate,ZoneDetail> dateZoneFarthestMap){
        Map remainingFarePerWeekMap = new HashMap<Integer, Double>();
        Map totalFarePerWeekMap = new HashMap<Integer, Double>();
        if(dateZoneFarthestMap == null )
            dateZoneFarthestMap = FareCalculatorUtil.computeFarthestZonePerDate(commuteDetailDTOS);
        Map<Integer, ZoneDetail> weekFarthestZoneMap = computeWeekFarthestMap(dateZoneFarthestMap);
        for (CommuteDetailDTO commuteDetailDTO : commuteDetailDTOS) {
            int weekNumber= FareCalculatorUtil.getWeekNo(commuteDetailDTO.getDate());
            ZoneDetail zoneDetail= weekFarthestZoneMap.get(weekNumber);
            double weekCapedFare=FareConfiguration.getZoneWeekFareData().get(zoneDetail.getFromZone()).get(zoneDetail.getToZone());
            var fare = (double)remainingFarePerWeekMap.getOrDefault(weekNumber, weekCapedFare);
            totalFarePerWeekMap.putIfAbsent(weekNumber, weekCapedFare);
            var remainingFare = fare - commuteDetailDTO.getFare();
            remainingFarePerWeekMap.put(weekNumber, remainingFare);
            if (remainingFare < 0) {
                if (fare > 0) commuteDetailDTO.setFare(fare);
                else commuteDetailDTO.setFare(0);
                remainingFarePerWeekMap.put(weekNumber, 0.0);
            }

        }
        logger.debug("Fare Computation After Running Weekly Caped Rules ");
        printFareDetail(Collections.unmodifiableList(commuteDetailDTOS));
      return  calculateTotalPerWeek(remainingFarePerWeekMap, totalFarePerWeekMap);

    }

    private Map<Integer, ZoneDetail> computeWeekFarthestMap(Map<LocalDate, ZoneDetail> dateZoneFarthestMap) {
        Map<Integer ,ZoneDetail> weekFarthestZoneMap= new HashMap<>();
        for (LocalDate localDate : dateZoneFarthestMap.keySet()) {
            int weekNo = FareCalculatorUtil.getWeekNo(localDate);
            ZoneDetail existingZoneDetail = dateZoneFarthestMap.get(localDate);
            weekFarthestZoneMap.putIfAbsent(weekNo, existingZoneDetail);
            ZoneDetail zoneDetail1 = weekFarthestZoneMap.get(weekNo);
            if (Math.abs(existingZoneDetail.getToZone() - existingZoneDetail.getFromZone()) >
                    Math.abs(zoneDetail1.getToZone() - zoneDetail1.getFromZone())) {
                weekFarthestZoneMap.put(weekNo, existingZoneDetail);
            }
        }
        return weekFarthestZoneMap;
    }

    double calculateTotalPerWeek(Map<Integer, Double> remainingWeekFareMap, Map<Integer, Double> totalWeekFareMap)
    {
        double sum=0.0;
        for (Map.Entry<Integer, Double> entry : remainingWeekFareMap.entrySet()) {
            int weekNo = entry.getKey();
            sum+=totalWeekFareMap.get(weekNo) - entry.getValue();
            totalWeekFareMap.put(weekNo, totalWeekFareMap.get(weekNo) - entry.getValue());
        }
        return sum;
    }
}
