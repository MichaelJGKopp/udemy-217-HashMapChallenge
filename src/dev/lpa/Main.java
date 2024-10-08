package dev.lpa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

  private final static Map<String, Location> map = new HashMap<>();
  private static Location current;

  public static void main(String[] args) {

    loadMap();
    current = map.get("road");
//    map.forEach((k, v) -> System.out.println(k + ": " + v));

    try (Scanner scanner = new Scanner(System.in)) {

      boolean isRunning = true;
      while(isRunning) {
        System.out.println("---------------------------------------");
        System.out.println("*** You are standing " + current.getDescription() + " ***\n" +
          "    From here, you can see:");
        current.getNextPlaces().forEach((k, v) ->
          System.out.println("    \u2022 A " + v + " to the " + getDirection(k) + " (" + k + ")" ));
        System.out.println("Enter direction to move in " + "\"N\", \"E\", \"S\", \"W\" " +
          "or \"Q\" to quit");
        String input = scanner.nextLine().trim().substring(0, 1).toUpperCase();
        System.out.println("---------------------------------------");
        switch(input) {
          case "Q" -> isRunning = false;
          case "N", "W", "E", "S" -> current = move(input);
          default -> System.out.println("Invalid input, try again.");
        }
      }

    }
  }

  private static String getDirection(String direction) {
    return switch(direction) {
      case "N" -> "North";
      case "E" -> "East";
      case "S" -> "South";
      case "W" -> "West";
      default -> "";
    };
  }

  private static Location move(String input) {
    String nextPlace = current.getNextPlaces().get(input);
//    System.out.println("NextPlace: " + nextPlace);
    System.out.println("... moving");
    return map.get(nextPlace);
  }


  private static void loadMap() {
    String info = """
    road,at the end of the road, W: hill, E:well house,S:valley,N:forest
    hill,on top of hill with a view in all directions,N:forest, E:road
    well house,inside a well house for a small spring,W:road,N:lake,S:stream
    valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
    forest,at the edge of a thick dark forest,S:road,E:lake
    lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
    stream,near a stream with a rocky bed,W:valley, N:well house""";

    try (Scanner scanner = new Scanner(info)) {

      while (scanner.hasNext()) {
        String[] input = scanner.nextLine().trim().split(",");
        Arrays.setAll(input, i -> input[i].trim());
//        System.out.println(Arrays.toString(input));
        Map<String, String> inputMap = new HashMap<>();
        for (int i = 2; i < input.length; i++) {
          String[] keyValue = input[i].split(":");
          inputMap.put(keyValue[0].trim(), keyValue[1].trim());
        }
        map.put(input[0], new Location(input[1], inputMap));
      }
    }
  }

//  private static Map.Entry<String, String> getEntry(String string) {
//    String[] temp = string.split(":");
//    return Map.entry(temp[0], temp[1]);
//  }
}
