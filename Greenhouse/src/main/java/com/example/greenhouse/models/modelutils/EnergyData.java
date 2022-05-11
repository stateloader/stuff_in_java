package com.example.greenhouse.models.modelutils;

import java.sql.Timestamp;

public class EnergyData {

  private int id;
  private Timestamp timestamp;
  private float price;
  public EnergyData() {}

  public EnergyData(int id, Timestamp timestamp, float price) {
    this.id = id;
    this.timestamp = timestamp;
    this.price = price;
  }

  public int getId() {return id;}
  public Timestamp getTimestamp() {return timestamp;}
  public float getPrice() {return price;}

  public void setId(int id) {this.id = id;}
  public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}
  public void setPrice(float price) {this.price = price;}
}
