import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution {
  public static void main(String[] args) {
    Permutations instance = new Permutations(new ArrayList<>() {{add(1);add(2);add(3);add(4);}});
    instance.getPermutations();
  }
}

class Permutations {
  List<List<Integer>> permutations;
  List<Integer> list;
  
  Permutations(List<Integer> list) {
    this.list = list;
    permutations = new ArrayList<>();
  }
  
  public void getPermutations() {
    findPermutations(new ArrayList<Integer>(), list);
    permutations.forEach(System.out::println);
    //return permutations;
  }
  
  public void findPermutations(List<Integer> permutation, List<Integer> remainingItems) {
    if (remainingItems.size() == 0) {
      permutations.add(new ArrayList<>(permutation));
    } else {
      IntStream.range(0, remainingItems.size())
        .forEach(index -> {
          permutation.add(remainingItems.get(index));
          remainingItems.remove(index);
          findPermutations(permutation, remainingItems);
          remainingItems.add(index, permutation.get(permutation.size() - 1));
          permutation.remove(permutation.size() - 1);
        });
    }
  }

}
