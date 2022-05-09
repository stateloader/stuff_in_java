package com.example.greenhouse.models;

public class SensorH {
  private int id;
  private float lastValue;
  private float averageValue;

  public SensorH(int id, float lastValue, float averageValue) {
    this.id = id;
    this.lastValue = lastValue;
    this.averageValue = averageValue;
  }

  public int getId() {return id;}
  public float getLastValue() {return lastValue;}
  public float getAverageValue() {return averageValue;}

  public void setId(int id) {this.id = id;}
  public void setLastValue(float lastValue) {this.lastValue = lastValue;}
  public void setAverageValue(float averageValue) {this.averageValue = averageValue;}
}
