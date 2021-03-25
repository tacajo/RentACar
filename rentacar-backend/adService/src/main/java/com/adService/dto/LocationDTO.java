package com.adService.dto;

public class LocationDTO {

    private Long ad;

    private double longitude;

    private double latitude;

    public Long getAd() {
        return ad;
    }

    public void setAd(Long ad) {
        this.ad = ad;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "ad=" + ad +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
