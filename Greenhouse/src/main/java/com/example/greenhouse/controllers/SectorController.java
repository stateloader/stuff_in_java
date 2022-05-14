package com.example.greenhouse.controllers;

import org.springframework.ui.Model;
import com.example.greenhouse.repositories.SectorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectorController {

  private static SectorRepository sectorRepository = new SectorRepository();

  @GetMapping("/")

  // Shows ("thymeleafed") index.html-file.
  public String showIndex() {
    return "index";
  }

  @GetMapping("/sectors")
  public String showIndex(Model model) {

    model.addAttribute("sectors", sectorRepository.getAllSectorData());
    return "sectors";
  }
}