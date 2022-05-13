package com.example.greenhouse.controllers;
import com.example.greenhouse.models.Sector;
import com.example.greenhouse.repositories.SectorRepository;
import com.example.greenhouse.repositories.SensorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SectorController {

  private static SectorRepository sectorRepository = new SectorRepository();

  @GetMapping("/")

  public String showSectors(Model model) {
    List<Sector> sectors = sectorRepository.getAllSectorData();
    model.addAttribute("sectors", sectors);
    return "home";

  }
  @GetMapping("/sector/{id}")

  public String showSector(@PathVariable("id") int id, Model model) {
    Sector sector = sectorRepository.getSector(id);
    model.addAttribute("sector", sector);
    return "sector";
  }
}