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
    model.addAttribute("energyTable", energyRepository.getEnergyTable());
    return "energy";
  }
  @GetMapping("/energy/add")
  public String addEnergyData(Model model){
    EnergyData energyData = new EnergyData();
    model.addAttribute("energyData", energyData);
    return "energy_form";
  }
  @PostMapping("/energy/update")
  public String updateEnergyTable(@ModelAttribute EnergyData energyData) {
    energyRepository.addEnergyData(energyData);
    return "redirect:/energy";
  }
}
