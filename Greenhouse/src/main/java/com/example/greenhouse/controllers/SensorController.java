package com.example.greenhouse.controllers;

import org.springframework.ui.Model;
import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SensorController {

  private static SensorRepository sensorRepository = new SensorRepository();

  @GetMapping("/sectors/sector/temperature/{id}")
  public String showSectorTemperature(@PathVariable("id") int sectorID, Model model) {

    model.addAttribute("sector", sensorRepository.getSectorName(sectorID));
    model.addAttribute("sensors", sensorRepository.getTemperatureReadingBySector(sectorID));
    model.addAttribute("lastTemperatureIndex", sensorRepository.getSizeTemperatureData());
    model.addAttribute("temperatureAverage", sensorRepository.getAverageTemperature(sectorID));

    return "temperature";
  }

  @GetMapping("/sectors/sector/humidity/{id}")
  public String showSectorHumidity(@PathVariable("id") int sectorID, Model model) {

    model.addAttribute("sector", sensorRepository.getSectorName(sectorID));
    model.addAttribute("sensors", sensorRepository.getHumidityReadingBySector(sectorID));
    model.addAttribute("lastHumidityIndex", sensorRepository.getSizeHumidityData());
    model.addAttribute("humidityAverage", sensorRepository.getAverageHumidity(sectorID));
    return "humidity";
  }

  @GetMapping("/dashboard")
  public String showDashboard(@PathVariable("id") int sectorID, Model model) {
    return "dashboard";
  }
}
