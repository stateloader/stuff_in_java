package com.example.greenhouse.models;

import java.io.Serializable;

public class Sensor implements Serializable {
//Klass för modellering av instanser från tabellen "sensor".

  private int id;
  private String type;

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
