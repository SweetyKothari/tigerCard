package com.test.dto;


public class ZoneDetail {
    Integer fromZone;
    Integer toZone;

    public Integer getFromZone() {
        return fromZone;
    }

    public void setFromZone(Integer fromZone) {
        this.fromZone = fromZone;
    }

    public Integer getToZone() {
        return toZone;
    }

    public void setToZone(Integer toZone) {
        this.toZone = toZone;
    }

    public ZoneDetail(Integer fromZone, Integer toZone) {
        this.fromZone = fromZone;
        this.toZone = toZone;
    }
}
