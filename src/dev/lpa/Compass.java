package dev.lpa;

public enum Compass {
  E, N, S, W;

  private static final String[] directions = {"East", "North", "South", "West"};

  public String getString() {
    return directions[this.ordinal()];
  }
}
