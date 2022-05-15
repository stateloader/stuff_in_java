package com.example.greenhouse.controllers;

import com.example.greenhouse.repositories.EnergyRepository;
import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
//Största omvägen världen skådat för att fånga de senaste värdena temp/humid från varje sektor plus energy.

  @GetMapping("/")
  // Läser in "thymeleaf:ade" html-filen index.html tillsammans med model-attributen.
  public String showIndex(Model model) {

    SensorRepository sensor1 = new SensorRepository();
    sensor1.fetchTemperatureReadingBySector(1);
    sensor1.fetchHumidityReadingBySector(1);

    SensorRepository sensor2 = new SensorRepository();
    sensor2.fetchTemperatureReadingBySector(2);
    sensor2.fetchHumidityReadingBySector(2);

    SensorRepository sensor3 = new SensorRepository();
    sensor3.fetchTemperatureReadingBySector(3);
    sensor3.fetchHumidityReadingBySector(3);

    EnergyRepository energy = new EnergyRepository();
    energy.getAllEnergyData();

    model.addAttribute("energy_last", energy.getLastEnergyData());
    model.addAttribute("sensor1temp", sensor1.getLastTemperatureData());
    model.addAttribute("sensor1humd", sensor1.getLastHumidityData());
    model.addAttribute("sensor2temp", sensor2.getLastTemperatureData());
    model.addAttribute("sensor2humd", sensor2.getLastHumidityData());
    model.addAttribute("sensor3temp", sensor3.getLastTemperatureData());
    model.addAttribute("sensor3humd", sensor3.getLastHumidityData());

    return "index";
  }
}