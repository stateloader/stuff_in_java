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

    // Fetches all energy-data and its average into the ("thymeleafed") energy.html-file togheter with
    // an EnergyData-instance (used for input and SQL insertion). Attribute "lastEnergyIndex" used for
    // showing latest entry in energyTable.

    model.addAttribute("energyTable", energyRepository.getAllEnergyData());
    model.addAttribute("energyData", energyRepository.getEnergyInstance());
    model.addAttribute("lastEnergyIndex", energyRepository.getSizeEnergyTable());
    model.addAttribute("energyAverage", energyRepository.getAveragePrice());
    return "energy";
  }

  @PostMapping("/energy/update")
  public String updateEnergyTable(@ModelAttribute EnergyData energyData) {
    energyRepository.addEnergyData(energyData);
    return "redirect:/energy";
  }
}
