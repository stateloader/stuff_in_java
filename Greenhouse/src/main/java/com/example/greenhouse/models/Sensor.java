package com.example.greenhouse.models;

public class Sensor {

  private int id;
  private String type;

  // constructors
  public Sensor() {}

  public Sensor(int id, String type) {
    this.id = id;
    this.type = type;
  }

  // getters
  public String getType() {return type;}
  public int getId() {return id;}

  // setters
  public void setId(int id) {this.id = id;}
  public void setType(String type) {this.type = type;}
}
