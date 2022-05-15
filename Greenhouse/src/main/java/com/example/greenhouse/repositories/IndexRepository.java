package com.example.greenhouse.repositories;

public class IndexRepository {

  public IndexRepository() {}

  public void getLastTest() {

    EnergyRepository energyRepository = new EnergyRepository();
    SectorRepository sectorRepository = new SectorRepository();
    SensorRepository sensorRepository = new SensorRepository();

    energyRepository.getAllEnergyData();
  }
}
