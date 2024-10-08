package dev.lpa;

public enum Direction {
  N("North"), E("East"), W("West"), S("South");

  private final String fullName;

  Direction(String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }
}
