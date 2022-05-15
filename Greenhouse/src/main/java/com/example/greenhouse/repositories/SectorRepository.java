package com.example.greenhouse.repositories;
import com.example.greenhouse.models.Sector;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.List;
//Repository/DAO som hanterar query- och datahanteringslogik rörande DB:ns "sector"-tabell.

public class SectorRepository {

  public SectorRepository() {}

  public List<Sector> getAllSectorData() {

    // Klasserna Connect och Queries återfinns och förklaras i sub-paket "repoutils".
    Connect connect = new Connect();
    Queries queries = new Queries();

    // Connection upprättas före en lista skapas av "sector"-tabellens samtliga instanser före retur.
    Connection connection = connect.connectDatabase();

    return queries.getSectorTable(connection);
  }
}