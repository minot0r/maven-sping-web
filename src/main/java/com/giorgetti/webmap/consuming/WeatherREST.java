package com.giorgetti.webmap.consuming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherREST {

    @JsonProperty("forecast")
    private WeatherRESTForecast[] forecasts;

    @JsonProperty("city")
    private Map<String, Object> cityDetails;

    public WeatherRESTForecast[] getForecasts() {
        return forecasts;
    }

    public String getCityName() {
        return (String) cityDetails.get("name");
    }

    public int getPostCode() {
        return (int) cityDetails.get("cp");
    }

    public int getAltitude() {
        return (int) cityDetails.get("altitude");
    }
}
