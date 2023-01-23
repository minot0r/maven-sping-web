package com.giorgetti.webmap.consuming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherRESTForecast {

    @JsonProperty
    int day;

    @JsonProperty
    int tmin;

    @JsonProperty
    int tmax;

    @JsonProperty
    int weather;

    public int getDay() {
        return day;
    }

    public int getTempMin() {
        return tmin;
    }

    public int getTempMax() {
        return tmax;
    }

    public int getWeather() {
        return weather;
    }
}
