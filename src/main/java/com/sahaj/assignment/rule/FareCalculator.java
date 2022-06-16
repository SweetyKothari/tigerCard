package com.sahaj.assignment.rule;

import com.sahaj.assignment.dto.CommuteDetailDTO;
import com.sahaj.assignment.dto.ZoneDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Class to define common method for each type of caped
 */
public abstract class FareCalculator {

    public abstract double calculateFare(List<CommuteDetailDTO> commuteDetailDTOS, Map<LocalDate,ZoneDetail> dateZoneFarthestMap);
    public abstract void setNextFareCalculator(FareCalculator fareCalculator);

}
