package com.example.greenhouse.controllers;

import org.springframework.ui.Model;
import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensorController {

  private static SensorRepository sensorRepository = new SensorRepository();

  @GetMapping("/sensors")
  public String showSensors(Model model) {
    model.addAttribute("sensors", sensorRepository.getAllSensorData(2));
    return "sensors";
  }
}
