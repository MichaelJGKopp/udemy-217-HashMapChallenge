package dev.lpa;

import java.util.Map;

public record Location(String description, Map<Compass, String> nextPlaces) {}