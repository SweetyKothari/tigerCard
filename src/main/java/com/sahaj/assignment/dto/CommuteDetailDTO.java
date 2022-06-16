package com.sahaj.assignment.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommuteDetailDTO {

    static Logger logger = LoggerFactory.getLogger(CommuteDetailDTO.class);

    private LocalDate date;
    private LocalTime time;
    private int fromZone;
    private int toZone;
    private double fare;

    public static class CommuteDetailDTOBuilder{
        private String date;
        private String time;
        private int fromZone;
        private int toZone;

        public CommuteDetailDTOBuilder(String date, String time,int fromZone,int toZone) {
            this.date = date;
            this.time = time;
            this.fromZone=fromZone;
            this.toZone=toZone;
        }
        public CommuteDetailDTOBuilder setDate(String date){
           this.date=date;
           return this;
        }
        public CommuteDetailDTOBuilder setTime(String time){
            this.time=time;
            return this;
        }
        public CommuteDetailDTOBuilder setFromZone(int fromZone){
            this.fromZone=fromZone;
            return this;
        }
        public CommuteDetailDTOBuilder setToZone(int toZone){
            this.toZone=toZone;
            return this;
        }
        public CommuteDetailDTO build(){
            return new CommuteDetailDTO(this);
        }

    }

    private CommuteDetailDTO(CommuteDetailDTOBuilder commuteDetailDTOBuilder){
        try {
            this.date = LocalDate.parse(commuteDetailDTOBuilder.date);
            this.time = LocalTime.parse(commuteDetailDTOBuilder.time);
            this.fromZone = commuteDetailDTOBuilder.fromZone;
            this.toZone = commuteDetailDTOBuilder.toZone;
        }
        catch(DateTimeParseException exception){
            logger.error(exception.getMessage() +"\n " + " Excepted Date Format yyyy-mm-dd  & time format should be hh:mm ");
            System.exit(1);
        }
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getFromZone() {
        return fromZone;
    }

    public int getToZone() {
        return toZone;
    }

    public double getFare() {
        return fare;
    }
}
