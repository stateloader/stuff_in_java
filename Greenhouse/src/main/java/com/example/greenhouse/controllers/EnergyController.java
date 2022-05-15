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

  private static EnergyRepository energyRepository = new EnergyRepository();

  @GetMapping("/energy")
  public String showEnergyTable(Model model) {

    energyRepository.getAllEnergyData();

    model.addAttribute("energyTable", energyRepository.getEnergyTable());
    model.addAttribute("energyData", new EnergyData());
    model.addAttribute("lastEnergyData", energyRepository.getLastEnergyData());
    model.addAttribute("averagePrice", energyRepository.getAveragePrice());
    return "energy";
  }

  @PostMapping("/energy/update")
  public String updateEnergyTable(@ModelAttribute EnergyData energyData) {
    energyRepository.addEnergyData(energyData);
    return "redirect:/energy";
  }
}


// Fetches all energy-data and its average into the ("thymeleafed") energy.html-file togheter with
// an EnergyData-instance (used for input and SQL insertion). Attribute "lastEnergyIndex" used for
// showing latest entry in energyTable.
