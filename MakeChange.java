import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution {
  public static void main(String[] args) {
    MakeChange makeChangeInstance = new MakeChange(new int[]{1,2,3,4}, 12);
    makeChangeInstance.makeChange();
  }
}


class MakeChange {
  int[] denoms;
  int amount;
  List<List<Integer>> change; 
  MakeChange(int[] denoms, int amount) {
    this.denoms = denoms;
    this.amount = amount;
    change = new ArrayList<>();
  }
  
  public void makeChange() {
    makeChange(new ArrayList<Integer>(), 0, amount);
    change.forEach(System.out::println);
  }

  private void makeChange(List<Integer> list, int index, int amountRemaining) {
    if (index == denoms.length) {
      if (amountRemaining == 0) change.add(new ArrayList<>(list));
    } else {
      int currentDenom = denoms[index];
      for (int i = 0; i * currentDenom <= amountRemaining; i++) {
        int addTimes = i;
        int removeTimes = i;
        while (addTimes -- > 0) {
          list.add(currentDenom);
        }
        makeChange(list, index+1, amountRemaining - i*currentDenom);
        while (removeTimes -- > 0) {
          list.remove(list.size() - 1);
        }
      }
    }
  }
}
