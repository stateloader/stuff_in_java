package com.example.greenhouse.models.modelutils;

import java.text.DecimalFormat;
import java.util.List;

public class Calculator {
//Trodde mer skulle landa i denna klass men slutade i en enda method som räknar ut medelvärdet på en lista av floats.

  public Calculator() {}

  public float calculateAverage(List<Float> readings) {

    float sum = 0, avg = 0;
    for (int i = 0; i < readings.size(); i++)
      sum += readings.get(i);
    return avg = (sum / readings.size());
  }
}
