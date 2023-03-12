package com.fengwenyi.javademo.mmsi;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-03-12
 */
public class MmsiBo {
    private String mmsi;
    private Float lon;
    private Float lat;

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "MmsiBo{" +
                "mmsi='" + mmsi + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
