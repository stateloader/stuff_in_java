package com.example.greenhouse.models.modelutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

  private final int MAX_DATA_ENTRY = 21;

  public Generator() {}

  public int getMAX_DATA_ENTRY() {return MAX_DATA_ENTRY;}

  public List<Float> generateRandFloats(int lowBound, int maxBound) {

    Random rand = new Random();
    List<Float> randomFloats = new ArrayList<Float>();
    for (int i = 0; i < MAX_DATA_ENTRY; i++)
      randomFloats.add(rand.nextFloat(lowBound, maxBound));
    return randomFloats;
  }

  public List<String> generateTimeStamps() {

    int dayCount = 1, idxCount = 0;
    String year = "2022-", month = "06-";
    String[] time = {" 06:00:00", " 12:00:00", " 18:00:00"};

    List<String> timestamps = new ArrayList<>();

    for (int i = 0; i < MAX_DATA_ENTRY; i++) {
      if (idxCount == 3) {dayCount++; idxCount = 0;}
      String stamp = year + month + "0" + dayCount + time[idxCount++].toString();
      timestamps.add(stamp);
    }
    return timestamps;
  }
}
