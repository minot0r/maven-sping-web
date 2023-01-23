package com.giorgetti.webmap.consuming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GouvRESTAddress {

    @JsonProperty
    private String label;
    @JsonProperty

    private float score;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String postcode;
    @JsonProperty
    private String citycode;
    @JsonProperty
    private float x;
    @JsonProperty
    private float y;
    @JsonProperty
    private String city;
    @JsonProperty
    private String district;
    @JsonProperty
    private String context;
    @JsonProperty
    private String type;
    @JsonProperty
    private float importance;

    private double latitude;
    private double longitude;

    @JsonProperty
    private String street;

    public String getFullAddress() {
        return label;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
