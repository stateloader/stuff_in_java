package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnergyRepository {

  public EnergyRepository() {}

  public List<EnergyData> getAllEnergyData() {

    Connect connect = new Connect();
    List<EnergyData> energyTable = new ArrayList<>();

    try {
      Connection connection = connect.loadConnection();
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(Queries.ALL_ENERGY_DATA);

      while (resultset.next()) {
        int id = resultset.getInt("id");
        float price = resultset.getFloat("price");
        Timestamp timestamp = resultset.getTimestamp("timestamp");
        energyTable.add(new EnergyData(id, price, timestamp));
      }

      return energyTable;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }

  public boolean addEnergyData(EnergyData energyData) {

    int rowchanged = 0;
    Connect connect = new Connect();

    try {
      Connection connection = connect.loadConnection();
      PreparedStatement statement = connection.prepareStatement(Queries.ADD_ENERGY_DATA);
      statement.setFloat(1, energyData.getPrice());
      rowchanged = statement.executeUpdate();
      if (rowchanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }

  public EnergyData getEnergyData() {
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