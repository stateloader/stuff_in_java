package com.example.greenhouse.models;

public class Sector {

  private int sectionID;
  private String sectionName;

  public Sector() {}

  public Sector(int sectionID, String sectionName) {
    this.sectionID = sectionID;
    this.sectionName = sectionName;
  }

  public int getSectionID() {return sectionID;}
  public String getSectionName() {return sectionName;}

  public void setSectionID(int sectionID) {this.sectionID = sectionID;}
  public void setSectionName(String sectionName) {this.sectionName = sectionName;}
}
