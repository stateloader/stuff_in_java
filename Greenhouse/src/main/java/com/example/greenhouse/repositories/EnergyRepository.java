package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.repoutils.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnergyRepository {

  private static final String QUERY_ALL = "select * from energy";
  private static final String QUERY_ADD = "insert into energy (NOW(), price) values (?, ?)";
  private static final String QUERY_DEL = "delete from sector where id = ?";

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
        Timestamp timestamp = resultset.getTimestamp("timestamp");
        float price = resultset.getFloat("price");
        energyTable.add(new EnergyData(id, timestamp, price));
      }
      return energyTable;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }
  public boolean addEnergyData(EnergyData energyData) {

    int rowChanged = 0;
    Connect connect = new Connect();

    try {
      Connection connection = connect.loadConnection();
      PreparedStatement statement = connection.prepareStatement(QUERY_ADD);

      statement.setTimestamp(1, energyData.getTimestamp());
      statement.setFloat(2, energyData.getPrice());

      rowChanged = statement.executeUpdate();

      if (rowChanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }

  public boolean delEnergyData(int id) {

    int rowChanged = 0;
    Connect connect = new Connect();

    try {
      Connection connection = connect.loadConnection();
      PreparedStatement statement = connection.prepareStatement(QUERY_DEL);
      statement.setInt(1, id);
      rowChanged = statement.executeUpdate();

      if (rowChanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }
}