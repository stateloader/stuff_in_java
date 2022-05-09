package com.example.greenhouse.models.utils;

public class SampleT {
  private int id;
  private float sample;
  private String timestamp;

  public SampleT() {}

  public SampleT(int id, float sample, String timestamp) {
    this.id = id;
    this.sample = sample;
    this.timestamp = timestamp;
  }

  public int getId() {return id;}
  public float getSample() {return sample;}
  public String getTimestamp() {return timestamp;}

  public void setId(int id) {this.id = id;}
  public void setSample(float sample) {this.sample = sample;}
  public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
}
