package com.example.greenhouse.repositories;
import com.example.greenhouse.models.Sector;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.List;

public class SectorRepository {

  public SectorRepository() {}

  public List<Sector> getAllSectorData() {

    // Queries all sectors and returns them in a list [see class Query].

    Connect connect = new Connect();
    Queries queries = new Queries();
    Connection connection = connect.loadConnection();

    return queries.getSectorTable(connection);
  }
}