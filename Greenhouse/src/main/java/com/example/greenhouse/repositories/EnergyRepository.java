package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.repoutils.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnergyRepository {

  // SQL syntax-strings to read the entire energy_data-table and add new instance.

  private static final String QUERY_ALL = "select * from energy_data";
  private static final String QUERY_PRC = "select price from energy_data";
  private static final String QUERY_ADD = "insert into energy_data (price, timestamp) values (?, NOW())";


  public EnergyRepository() {}

  public List<EnergyData> getEnergyTable() {

    Connect connect = new Connect();
    List<EnergyData> energyTable = new ArrayList<>();

    try {
      Connection connection = connect.loadConnection();
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(QUERY_ALL);

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
  public List<Float> getPriceData() {
    List<EnergyData> energyTable = getEnergyTable();
    List<Float> prices = Collections.singletonList(energyTable.get(1).getPrice());
    return prices;
  }

  public boolean addEnergyData(EnergyData energyData) {

    int rowchanged = 0;
    Connect connect = new Connect();

    try {
      Connection connection = connect.loadConnection();
      PreparedStatement statement = connection.prepareStatement(QUERY_ADD);

      statement.setFloat(1, energyData.getPrice());

      rowchanged = statement.executeUpdate();

      if (rowchanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }
}