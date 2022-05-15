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
  private List<EnergyData> energyTable;
  private EnergyData lastEnergyData;
  private float averagePrice;

  public EnergyRepository() {}

  // getters
  public int getSizeEnergyTable() {return sizeEnergyTable;}
  public List<EnergyData> getEnergyTable() {return energyTable;}
  public EnergyData getLastEnergyData() {return lastEnergyData;}
  public float getAveragePrice() {return averagePrice;}

  // setters
  public void setSizeEnergyTable(int sizeEnergyTable) {this.sizeEnergyTable = sizeEnergyTable;}
  public void setEnergyTable(List<EnergyData> energyTable) {this.energyTable = energyTable;}
  public void setLastEnergyData(EnergyData lastEnergyData) {this.lastEnergyData = lastEnergyData;}
  public void setAveragePrice(float averagePrice) {this.averagePrice = averagePrice;}

  public void getAllEnergyData() {

    Connect connect = new Connect();
    Queries queries = new Queries();

    Connection connection = connect.loadConnection();

    setEnergyTable(queries.getEnergyTable(connection));
    setSizeEnergyTable(getEnergyTable().size());
    setLastEnergyData(getEnergyTable().get(getSizeEnergyTable() - 1));
    setAveragePrice(calculateAveragePrice());
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

  private float calculateAveragePrice() {

    Calculator calculator = new Calculator();
    List<Float> prices = new ArrayList<>();

    for (EnergyData data : getEnergyTable())
      prices.add(data.getPrice());

    return calculator.calculateAverage(prices);
  }
}