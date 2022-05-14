package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnergyRepository {

  private int sizeEnergyTable;

  public EnergyRepository() {}

  //getters setters
  public int getSizeEnergyTable() {return sizeEnergyTable;}
  public void setSizeEnergyTable(int sizeEnergyTable) {this.sizeEnergyTable = sizeEnergyTable;}

  public List<EnergyData> getAllEnergyData() {

    Connect connect = new Connect();
    Queries queries = new Queries();
    List<EnergyData> energyData;

    Connection connection = connect.loadConnection();

    energyData = queries.getEnergyTable(connection);
    setSizeEnergyTable(energyData.size());

    return energyData;
  }

  public boolean addEnergyData(EnergyData energyData) {

    int rowchanged = 0;
    Connect connect = new Connect();
    Connection connection = connect.loadConnection();
    try {
      PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO energy_data (price, timestamp) VALUES (?, NOW())");
      statement.setFloat(1, energyData.getPrice());
      rowchanged = statement.executeUpdate();
      if (rowchanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }

  public EnergyData getEnergyInstance() {
    return new EnergyData();
  }

  public float getAveragePrice() {

    Calculator calculator = new Calculator();
    List<Float> prices = new ArrayList<>();
    List<EnergyData> energyTable = getAllEnergyData();

    for (EnergyData data : energyTable)
      prices.add(data.getPrice());

    return calculator.calculateAverage(prices);
  }
}