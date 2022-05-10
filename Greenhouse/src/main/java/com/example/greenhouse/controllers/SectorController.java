package com.example.greenhouse.controllers;

import com.example.greenhouse.models.Sector;
import com.example.greenhouse.repositories.repoutils.SysCall;
import com.example.greenhouse.repositories.SectorDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greenhouse")
public class SectorController {

  private static SectorDAO sectorDAO = new SectorDAO();

  @GetMapping("/sectors")
  public List<Sector> getSectors() {
    return sectorDAO.getAllSectors();
  }

  @GetMapping("/sectors/{id}")
  public Sector getSector(@PathVariable("id") int id) {
    return sectorDAO.getSector(id);
  }

  @DeleteMapping("/sectors/{id}/delete")
  public SysCall deleteSector(@PathVariable("id") int id){
    SysCall call = new SysCall("sector deleted", sectorDAO.delSector(id));
    return call;
  }

  @PostMapping("/sectors/add")
  public SysCall addSector(@RequestBody Sector sector){
    SysCall call = new SysCall("sector added", sectorDAO.setSector(sector));
    return call;
  }
}