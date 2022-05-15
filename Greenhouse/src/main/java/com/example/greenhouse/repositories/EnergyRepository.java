package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnergyRepository {
//Repository/DAO som hanterar query- och datahanteringslogik rörande DB:ns "energy_data"-tabell.

  // query-sträng förklarad i add-methoden längre ned.
  private static final String ADD_ENERGY_DATA = "INSERT INTO energy_data (price, timestamp) VALUES (?, NOW())";

  private List<EnergyData> energyTable;   // nuvarande tabell.
  private EnergyData lastEnergyData;      // tabellens senast tillagda instans.
  private int sizeEnergyTable;            // tabellens senaste storlek.
  private float averagePrice;             // tabellens senaste medelvärde (el-priser).

  public EnergyRepository() {}

  // Getters.
  public int getSizeEnergyTable() {return sizeEnergyTable;}
  public List<EnergyData> getEnergyTable() {return energyTable;}
  public EnergyData getLastEnergyData() {return lastEnergyData;}
  public float getAveragePrice() {return averagePrice;}

  // Setters.
  public void setSizeEnergyTable(int sizeEnergyTable) {this.sizeEnergyTable = sizeEnergyTable;}
  public void setEnergyTable(List<EnergyData> energyTable) {this.energyTable = energyTable;}
  public void setLastEnergyData(EnergyData lastEnergyData) {this.lastEnergyData = lastEnergyData;}
  public void setAveragePrice(float averagePrice) {this.averagePrice = averagePrice;}

  public void getAllEnergyData() {

    // Klasserna Connect och Queries återfinns och förklaras i sub-paket "repoutils".
    Connect connect = new Connect();
    Queries queries = new Queries();

    // Connection upprättas före en lista skapas av energy-tabellens instanser.
    Connection connection = connect.connectDatabase();
    setEnergyTable(queries.getEnergyTable(connection));

    // Tabellens storlek, senaste (tillagda) data och medelvärde "setts".
    setSizeEnergyTable(getEnergyTable().size());
    setLastEnergyData(getEnergyTable().get(getSizeEnergyTable() - 1));
    setAveragePrice(calculateAveragePrice());
  }

  public boolean addEnergyData(EnergyData energyData) {
    // Applikationens enda "add". Hade helst velat haft lejonparten av all logik i denna method (precis som övriga
    // queries) i "Query"-klassen men IntelliJ/Java slog ifrån mina olika försök. I nuläget heller Ingen felhantering
    // på plats som möter upp methodens bool-return (Likt din Response-klass).

    int rowchanged = 0;
    Connect connect = new Connect();
    Connection connection = connect.connectDatabase();
    try {
      PreparedStatement statement = connection.prepareStatement(ADD_ENERGY_DATA);
      statement.setFloat(1, energyData.getPrice());
      rowchanged = statement.executeUpdate();
      if (rowchanged == 1) return true;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
    return false;
  }

  private float calculateAveragePrice() {
    // Logik för medelvärdesberäkning av värdena "energy_data"-tabellens pris-kolumn.

    Calculator calculator = new Calculator();
    List<Float> prices = new ArrayList<>();
    for (EnergyData data : getEnergyTable())
      prices.add(data.getPrice());
    return calculator.calculateAverage(prices);
  }
}