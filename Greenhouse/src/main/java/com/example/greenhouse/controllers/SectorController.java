package com.example.greenhouse.controllers;

import org.springframework.ui.Model;
import com.example.greenhouse.repositories.SectorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectorController {
//Controller för sector-viewing.

  private static SectorRepository sectorRepository = new SectorRepository();

  @GetMapping("/sectors")
  public String showIndex(Model model) {
    // Method som läser in all data från SQL-tabellen "sector".

    model.addAttribute("sectors", sectorRepository.getAllSectorData());

    return "sectors";
  }
}