package com.example.greenhouse.models;

import java.io.Serializable;

public class Sector implements Serializable {
//Klass för modellering av instanser från tabellen "sector".

  private int id;
  private String name;

  public Sector() {}

  public Sector(int id, String name) {
    this.id = id;
    this.name = name;
  }

  // getters.
  public int getId() {return id;}
  public String getName() {return name;}

  // setters.
  public void setId(int id) {this.id = id;}
  public void setName(String name) {this.name = name;}
}
