package com.example.greenhouse.controllers;
import com.example.greenhouse.models.Sector;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.EnergyRepository;
import com.example.greenhouse.repositories.SectorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FacilityController {

  private static SectorRepository sectorRepository = new SectorRepository();
  private static EnergyRepository energyRepository = new EnergyRepository();

  @GetMapping("/")
  public String showFacility(Model model) {
    List<Sector> sectors = sectorRepository.getSectorTable();
    model.addAttribute("sectors", sectors);
    return "home";

  }

  @GetMapping("/sector/{id}")
  public String showSector(@PathVariable("id") int id, Model model) {
    Sector sector = sectorRepository.getSector(id);
    model.addAttribute("sector", sector);
    return "sector";
  }

  @GetMapping("/energy")
  public String showEnergyTable(Model model) {
    List<EnergyData> energyTable = energyRepository.getEnergyTable();
    model.addAttribute("energyTable", energyTable);
    return "energy";
  }
}