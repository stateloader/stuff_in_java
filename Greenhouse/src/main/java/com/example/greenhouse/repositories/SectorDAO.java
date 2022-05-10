package com.example.greenhouse.repositories;
import com.example.greenhouse.models.Sector;
import com.example.greenhouse.repositories.repoutils.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDAO {

  private static final String QUERY_ALL = "select * from sector";
  private static final String QUERY_ADD = "insert into sector (name) values (?)";
  private static final String QUERY_DEL = "delete from sector where id = ?";

  public SectorDAO () {}

  public List<Sector> getAllSectors() {

    Connect connect = new Connect();
    List<Sector>sectors = new ArrayList<>();

    try {
      Connection connection = connect.loadConnection();
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(QUERY_ALL);

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
  public Sector getSector(int id) {

    List<Sector> sectors = getAllSectors();

    for (int i = 0; i < sectors.size(); i++) {
      if (sectors.get(i).getId() == id)
        return sectors.get(i);
    }
    return null;
  }

  public boolean setSector(Sector sector) {

    int rowChanged = 0;
    Connect connect = new Connect();

    try {
      Connection connection = connect.loadConnection();
      PreparedStatement statement = connection.prepareStatement(QUERY_ADD);
      statement.setString(1, sector.getName());
      rowChanged = statement.executeUpdate();

      if (rowChanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }

  public boolean delSector(int id) {
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