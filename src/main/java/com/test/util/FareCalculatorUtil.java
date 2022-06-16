package com.test.util;

import com.test.config.FareConfiguration;
import com.test.dto.CommuteDetailDTO;
import com.test.dto.ZoneDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FareCalculatorUtil {

    static Logger logger = LoggerFactory.getLogger(FareCalculatorUtil.class.getName());

    public static boolean isPeakTime(LocalDate date, LocalTime time){
       Map<LocalTime ,LocalTime> map= FareConfiguration.getPeakHrFareData().get(date.getDayOfWeek());
       for(LocalTime key: map.keySet()){
           if((time.isAfter(key) || time.equals(key)) && (time.equals(map.get(key)) || time.isBefore(map.get(key))) ){
               return true;
           }
       }
       return false;

    }

    public static int getWeekNo(LocalDate localDate)
    {
        return localDate.get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    public static Map<LocalDate, ZoneDetail> computeFarthestZonePerDate(List<CommuteDetailDTO> commuteDetailDTOS) {
        Map<LocalDate ,ZoneDetail> dateZoneFarthestMap = new HashMap<>();
        for(CommuteDetailDTO commuteDetailDTO: commuteDetailDTOS) {
            ZoneDetail existingZoneDetail = dateZoneFarthestMap.get(commuteDetailDTO.getDate());
            if (existingZoneDetail == null) {
                existingZoneDetail = new ZoneDetail(commuteDetailDTO.getFromZone(), commuteDetailDTO.getToZone());
                dateZoneFarthestMap.put(commuteDetailDTO.getDate(), existingZoneDetail);
            }
            if (Math.abs(commuteDetailDTO.getToZone() - commuteDetailDTO.getFromZone()) >
                    Math.abs(existingZoneDetail.getToZone() - existingZoneDetail.getFromZone())) {
                existingZoneDetail = new ZoneDetail(commuteDetailDTO.getFromZone(), commuteDetailDTO.getToZone());
                dateZoneFarthestMap.put(commuteDetailDTO.getDate(), existingZoneDetail);

            }
        }
        return dateZoneFarthestMap;
    }

    public static void printFareDetail(List<CommuteDetailDTO> commuteDetailDTOList){
        commuteDetailDTOList.stream().forEach(commuteDetail ->
                logger.debug(commuteDetail.getDate() + "|| "+commuteDetail.getTime() + " || "
                        +commuteDetail.getFromZone() +" || "+commuteDetail.getToZone() + " || "+commuteDetail.getFare()));
    }
}
