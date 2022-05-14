package com.example.greenhouse.repositories;
import com.example.greenhouse.models.Sector;
import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorRepository {

  public SectorRepository() {}

  public List<Sector> getAllSectorData() {

    Connect connect = new Connect();
    List<Sector>sectors = new ArrayList<>();

    try {
      Connection connection = connect.loadConnection();
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(Queries.ALL_SECTORS);

      while (resultset.next()) {
        int id = resultset.getInt("id");
        String name = resultset.getString("name");
        sectors.add(new Sector(id, name));
      }
      return sectors;
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }
  public Sector getSector(int sectorID) {

    List<Sector> sectors = getAllSectorData();

    for (int i = 0; i < sectors.size(); i++) {
      if (sectors.get(i).getId() == sectorID)
        return sectors.get(i);
    }
    return null;
  }
}