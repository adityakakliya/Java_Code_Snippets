import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution {
  public static void main(String[] args) {
    PowerSets instance = PowerSets.getInstance();
    instance.init(List.of(1,2,3,4));
    instance.getPowerSets();
    instance.powersets.forEach(System.out::println);
  }
}

class PowerSets {
  List<List<Integer>> powersets;
  List<Integer> list;
  private static PowerSets instance = new PowerSets();
  private PowerSets() {}
  
  public void init(List<Integer> list) {
    powersets = new ArrayList<>();
    this.list = list;
  }
  public static PowerSets getInstance() {
    return instance;
  }
  
  public List<List<Integer>> getPowerSets() {
    findPowerSets(0);
    return powersets;
  }
  
  public void findPowerSets(int index) {
    if (index == list.size()) {
      powersets.add(new ArrayList<>());
    } else {
      findPowerSets(index+1);
      int powersetSize = powersets.size();
      System.out.println("index " + index + " set size " + powersetSize);
      IntStream.range(0, powersetSize)
        .forEach(i -> {
          System.out.println("index " + index + "-" + powersets.get(i));
          List<Integer> newSet = new ArrayList<>(powersets.get(i));
          newSet.add(list.get(index));
          powersets.add(newSet);
        });
    }
  }
}

