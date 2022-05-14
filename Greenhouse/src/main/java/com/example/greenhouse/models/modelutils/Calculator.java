package com.example.greenhouse.models.modelutils;

import java.util.List;

public class Calculator {

  public Calculator() {}

  public float calculateAverage(List<Float> readings) {

    // Calculates and returns average from a List of float-values (parameter).

    float sum = 0, avg = 0;
    for (int i = 0; i < readings.size(); i++)
      sum += readings.get(i);
    return avg = (sum / readings.size());
  }
}
