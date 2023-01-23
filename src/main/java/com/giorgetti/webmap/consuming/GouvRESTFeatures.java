package com.giorgetti.webmap.consuming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GouvRESTFeatures {

    @JsonProperty
    private Map<String, Object>[] features;

    public GouvRESTAddress[] getRESTAddresses() {
        LinkedList<GouvRESTAddress> addresses = new LinkedList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Map<String, Object> feature : features) {
            GouvRESTAddress address = mapper.convertValue(feature.get("properties"), GouvRESTAddress.class);
            Map<String, Object> geometry = mapper.convertValue(feature.get("geometry"), Map.class);
            address.setLatitude(((ArrayList<Double>) geometry.get("coordinates")).get(1));
            address.setLongitude(((ArrayList<Double>) geometry.get("coordinates")).get(0));
            addresses.add(address);
        }

        return addresses.toArray(new GouvRESTAddress[addresses.size()]);
    }


}
