package com.giorgetti.webmap.controller;

import com.giorgetti.webmap.consuming.GouvRESTAddress;
import com.giorgetti.webmap.consuming.GouvRESTFeatures;
import com.giorgetti.webmap.consuming.WeatherREST;
import com.giorgetti.webmap.model.Address;
import com.giorgetti.webmap.model.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Date;

@Controller
public class WeatherController {

    private RestTemplate template;
    private String apiToken = "7966eb8a0c91f3e67d6cae27eb85cdeb76db6f0be6c5db56257903507c168f5d";

    public WeatherController(RestTemplate template) {
        this.template = template;
    }

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/address")
    public String getWeatherForm() {
        return "weather-form";
    }

    @PostMapping("/meteo")
    public String getWeatherForAddress(
            @RequestParam(name="address", required = true) String address,
            @RequestParam(name="searchedAlready", required = false, defaultValue = "false") boolean searchedAlready,
            Model model) {
        try {
            GouvRESTFeatures gouvFeatures = fetchGouvFeatures(address);
            WeatherREST weather = fetchWeather(gouvFeatures);
            model.addAttribute("weather", weather);
            if(!searchedAlready)
                addressRepository.save(new Address(address, "Valentin"));
        } catch (URISyntaxException e) {
            model.addAttribute("error", "Impossible de convertir en URI");
            System.err.println(e);
        }
        return "weather";
    }

    public GouvRESTFeatures fetchGouvFeatures(String address) throws URISyntaxException {
        URI gouvUri = new URI("http://api-adresse.data.gouv.fr/search/?q=" + URLEncoder.encode(address));
        return template.getForObject(gouvUri, GouvRESTFeatures.class);
    }

    public WeatherREST fetchWeather(GouvRESTFeatures gouvFeatures) throws URISyntaxException {
        double lat = gouvFeatures.getRESTAddresses()[0].getLatitude();
        double lon = gouvFeatures.getRESTAddresses()[0].getLongitude();
        URI weatherAPIUri = new URI(
                "https://api.meteo-concept.com/api/forecast/daily?token=" + apiToken + "&latlng="
                        + lat + "," + lon);
        return template.getForObject(weatherAPIUri, WeatherREST.class);
    }
}