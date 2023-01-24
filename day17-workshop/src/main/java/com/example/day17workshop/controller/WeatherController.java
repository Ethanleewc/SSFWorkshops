package com.example.day17workshop.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.day17workshop.model.Weather;
import com.example.day17workshop.service.WeatherService;


@Controller
@RequestMapping(path="/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam(required=true) String city,
        @RequestParam(required=false) String units,
        Model model) throws IOException{
        System.out.println("units > " + units);
        if(units == null)
            units = "metric";
            
        Optional<Weather> w= weatherSvc.getWeather(city, units);
        model.addAttribute("weather", w.get());
        return "weather";
    }
}