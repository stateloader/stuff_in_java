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
    // "modellerar" in attribut till energy.html för "viewing". Se EnergyRepository.

    // "energyTable", all data från tabellen "energy_data".
    // "energyData", instans av EnergyData, fylls i och läggs in i tabellen "energy_data".
    // "energyAverage, medelvärdet av samtliga energipriser.

    model.addAttribute("energyTable", energyRepository.getEnergyTable());
    model.addAttribute("energyData", energyRepository.getDataInstance());
    model.addAttribute("energyAverage", energyRepository.getAveragePrice());
    return "energy";
  }
  @PostMapping("/energy/update")
  public String updateEnergyTable(@ModelAttribute EnergyData energyData) {
    energyRepository.addEnergyData(energyData);
    return "redirect:/energy";
  }
}
