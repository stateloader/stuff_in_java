package com.example.greenhouse.models.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

  private final int MAX_SAMPLES = 21;

  public Generator() {}

  public int getMAX_SAMPLES() {return MAX_SAMPLES;}

  public List<float> generateRandomFloats(int lowBound, int maxBound) {

    Random rand = new Random();
    List<float> randomFloats = new ArrayList<float>();
    for (int i = 0; i < MAX_SAMPLES; i++)
      randomFloats.add(rand.nextFloat(lowBound, maxBound));
    return randomFloats;
  }

  public List<int> generateRandomInts(int lowBound, int maxBound) {

    Random rand = new Random();
    List<int> randomFloats = new ArrayList<int>();
    for (int i = 0; i < MAX_SAMPLES; i++)
      randomFloats.add(rand.nextInt(lowBound, maxBound));
    return randomFloats;
  }
  // MySQL DATETIME Format: 'YYYY-MM-DD hh:mm:ss'

  public List<String> generateTimeStamps() {

    int dayCount = 1;
    String year = "2022-", month = "06-";
    String[] time = {" 06:00:00", " 12:00:00", " 18:00:00"};

    List<String> timestamps = new ArrayList<>();

    for (int i = 0, j = 0; i < MAX_SAMPLES; i++, j++) {
      String stamp = year + month + "0" + dayCount + time[j].toString();
      timestamps.add(stamp);
      if (j == 2) j = 0;
    }
    return timestamps;
  }
}
