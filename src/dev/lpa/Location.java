package dev.lpa;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Location {
  private final String description;
  private final Map<String, String> nextPlaces;

  public Location(String description, Map<String, String> nextPlaces) {
    this.description = description;
    this.nextPlaces = new TreeMap<>(nextPlaces);
  }

  public String getDescription() {
    return description;
  }

  public Map<String, String> getNextPlaces() {
    return Collections.unmodifiableMap(nextPlaces);
  }

  @Override
  public String toString() {
    return "%s %s".formatted(description, nextPlaces);
  }
}
