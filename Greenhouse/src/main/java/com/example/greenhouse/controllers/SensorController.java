package com.example.greenhouse.controllers;

import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensorController {

  private static SensorRepository sensorRepository = new SensorRepository();

  @GetMapping("/sector/sensor/")
  public String showSensor(Model model) {
    model.addAttribute("sensorTable", sensorRepository.getAllSensorData());
    return "sensor";
  }
}