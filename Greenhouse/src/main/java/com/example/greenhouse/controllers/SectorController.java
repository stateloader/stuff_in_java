package com.example.greenhouse.controllers;

import com.example.greenhouse.repositories.SectorRepository;
import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectorController {

  private static SectorRepository sectorRepository = new SectorRepository();

  @GetMapping("/")
  public String showIndex() {
    return "index";
  }

  @GetMapping("/sectors")
  public String showIndex(Model model) {
    model.addAttribute("sectors", sectorRepository.getAllSectorData());
    return "sectors";
  }
}