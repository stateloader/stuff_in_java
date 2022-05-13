package com.example.greenhouse.models.modelutils;

import java.io.Serializable;
import java.sql.Timestamp;

public class EnergyData implements Serializable {

  private int id;
  private float price;
  private Timestamp timestamp;

  public EnergyData() {}

  public EnergyData(int id, float price, Timestamp timestamp) {
    this.id = id;
    this.price = price;
    this.timestamp = timestamp;
  }

  public int getId() {return id;}
  public float getPrice() {return price;}
  public Timestamp getTimestamp() {return timestamp;}

  public void setId(int id) {this.id = id;}
  public void setPrice(float price) {this.price = price;}
  public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}
}