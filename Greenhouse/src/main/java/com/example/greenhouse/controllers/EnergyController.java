package com.example.greenhouse.controllers;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.EnergyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnergyController {
//Controller för energidata-viewing.

  private static EnergyRepository energyRepository = new EnergyRepository();

  @GetMapping("/energy")
  public String showEnergyTable(Model model) {
    // Method inom vilken all data från SQL-tabellen "energy" läses in, data som lokalt sedan behandlas vidare för att
    // avkasta de attribut som står listat nedan. Dessa matas sedan in i den "thymeleaf:ade" html-filen energy för view.

    energyRepository.getAllEnergyData();

    model.addAttribute("energyTable", energyRepository.getEnergyTable());
    model.addAttribute("energyData", new EnergyData());
    model.addAttribute("lastEnergyData", energyRepository.getLastEnergyData());
    model.addAttribute("averagePrice", energyRepository.getAveragePrice());

    return "energy";
  }

  @PostMapping("/energy/update")
  public String updateEnergyTable(@ModelAttribute EnergyData energyData) {
    // Post-method kallad på under förfarandet med postandet av ett nytt (senaste) elpris.

    energyRepository.addEnergyData(energyData);

    return "redirect:/energy";
  }
}