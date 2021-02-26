package com.aditya;

import java.util.ArrayList;
import java.util.List;

public class Solution968 {

    public static void main(String[] args) {

    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> intervalIntersection = new ArrayList<>();
        int firstLength = firstList.length;
        int secondLength = secondList.length;

        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < firstLength && secondIndex < secondLength) {
            if (isIntersection(firstList, secondList, firstIndex, secondIndex)) {
                intervalIntersection.add(getIntersection(firstList, secondList, firstIndex, secondIndex));
            }
            if (firstList[firstIndex][1] <= secondList[secondIndex][1]) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }
        return intervalIntersection.toArray(new int[intervalIntersection.size()][]);
    }

    private boolean isIntersection (int[][] firstList, int[][] secondList, int firstIndex, int secondIndex) {
        if (secondList[secondIndex][0] <= firstList[firstIndex][0] && secondList[secondIndex][1] >= firstList[firstIndex][0]) return true;
        return firstList[firstIndex][0] <= secondList[secondIndex][0] && firstList[firstIndex][1] >= secondList[secondIndex][0];
    }

    private int[] getIntersection (int[][] firstList, int[][] secondList, int firstIndex, int secondIndex) {
        return new int[]{Math.max(firstList[firstIndex][0], secondList[secondIndex][0]),
                Math.min(firstList[firstIndex][1], secondList[secondIndex][1])
        };
    }
}
