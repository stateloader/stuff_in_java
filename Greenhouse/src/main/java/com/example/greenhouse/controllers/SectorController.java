package com.example.greenhouse.controllers;

import com.example.greenhouse.models.Sector;
import com.example.greenhouse.repositories.SectorDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sector_service")
public class SectorController {

  private static SectorDAO sectorDAO = new SectorDAO();

  @GetMapping("/sectors")
  // Returns list of Sectors.
  public List<Sector> getSectors() {
    return sectorDAO.getAllSectors();
  }
}
