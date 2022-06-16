package com.test.util;

import com.test.dto.CommuteDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class MockObjectHelper {

    public static  List<CommuteDetailDTO> mockCommuteDetailList(){
        List<CommuteDetailDTO> commuteDetailDTOS= new ArrayList<>();
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-01","10:30",1,1));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-01","10:45",1,1));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-01","18:09",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","18:09",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","10:09",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","12:00",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","13:00",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","19:09",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","20:09",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","21:00",1,2));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","11:00",1,2));
        return commuteDetailDTOS;
    }

    public static  List<CommuteDetailDTO> mockCommuteDetailListWithFare(){
        List<CommuteDetailDTO> commuteDetailDTOS= new ArrayList<>();
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-01","10:30",1,1,30.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-01","10:45",1,1,25.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-01","18:09",1,2,30.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","18:09",1,2,35.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","10:09",1,2,30.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","12:00",1,2,20.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-02","13:00",1,2,45.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","19:09",1,2,20.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","20:09",1,2,20.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","21:00",1,2,20.0));
        commuteDetailDTOS.add(getCommuteDetailDTO("2021-01-03","11:00",1,2,75));
        return commuteDetailDTOS;
    }

    public static CommuteDetailDTO getCommuteDetailDTO(String date,String time ,int fromZone,int toZone){
        return new CommuteDetailDTO.CommuteDetailDTOBuilder(date,time,fromZone,toZone).build();
    }
    public static CommuteDetailDTO getCommuteDetailDTO(String date,String time ,int fromZone,int toZone,double fare){
        CommuteDetailDTO commuteDetailDTO= new CommuteDetailDTO.CommuteDetailDTOBuilder(date,time,fromZone,toZone).build();
        commuteDetailDTO.setFare(fare);
        return commuteDetailDTO;
    }
}
