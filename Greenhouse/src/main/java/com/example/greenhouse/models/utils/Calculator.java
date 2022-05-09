package com.example.greenhouse.models.utils;

import java.util.List;

public class Calculator {

  public float calculateAverage(List<float> readings) {

    float sum = 0, avg = 0;

    for (int i = 0; i < readings.size(); i++)
      sum += readings.get(i);

    return avg = (sum / readings.size());

  }
}
