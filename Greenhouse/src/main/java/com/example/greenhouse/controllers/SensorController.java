package com.example.greenhouse.controllers;

import org.springframework.ui.Model;
import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SensorController {
//Controller för sensor-viewing.

  private static SensorRepository sensorRepository = new SensorRepository();

  @GetMapping("/sectors/sector/temperature/{id}")
  public String showSectorTemperature(@PathVariable("id") int sectorID, Model model) {
    // Method inom vilken all temperatur-data från SQL-tabellen "sensor_data" läses in som hör ihop med givet sectorID.
    // Denna data kommer lokalt sedan att behandlas vidare för att avkasta de attribut som står listade nedan. Dessa
    // matas sedan in i den "thymeleaf:ade" html-filen temperature för view.

    sensorRepository.fetchTemperatureReadingBySector(sectorID);

    model.addAttribute("sector", sensorRepository.getSectorName(sectorID));
    model.addAttribute("sensor", sensorRepository.getTemperatureTable());
    model.addAttribute("lastTemperatureData", sensorRepository.getLastTemperatureData());
    model.addAttribute("temperatureAverage", sensorRepository.getAverageTemperature());

    return "temperature";
  }

  @GetMapping("/sectors/sector/humidity/{id}")
  public String showSectorHumidity(@PathVariable("id") int sectorID, Model model) {
    // Method inom vilken all humidity-data från SQL-tabellen "sensor_data" läses in som hör ihop med givet sectorID.
    // Denna data kommer lokalt sedan att behandlas vidare för att avkasta de attribut som står listade nedan. Dessa
    // matas sedan in i den "thymeleaf:ade" html-filen temperature för view.

    sensorRepository.fetchHumidityReadingBySector(sectorID);

    model.addAttribute("sector", sensorRepository.getSectorName(sectorID));
    model.addAttribute("sensor", sensorRepository.getHumidityTable());
    model.addAttribute("lastHumidityData", sensorRepository.getLastHumidityData());
    model.addAttribute("humidityAverage", sensorRepository.getAverageHumidity());

    return "humidity";
  }
}
