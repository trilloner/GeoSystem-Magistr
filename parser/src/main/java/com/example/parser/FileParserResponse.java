package com.example.parser;

import org.springframework.data.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class FileParserResponse {
  private Pair<Double, Double> points;
  private double density;

  public double getDensity() {
    return density;
  }

  public void setDensity(double density) {
    this.density = density;
  }

  public Pair<Double, Double> getPoints() {
    return points;
  }

  public void setPoints(Pair<Double, Double> points) {
    this.points = points;
  }
}
