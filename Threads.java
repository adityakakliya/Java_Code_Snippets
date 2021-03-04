package com.aditya;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Threads {
    public static void main(String[] args) throws InterruptedException {
        SimilarStrings similarStrings = new SimilarStrings(List.of("abasf", "arefsbs", "asrfsddv", "aserfab", "abc", "abdefghtighaknt", "lkjhgfds", "oiuyrew", "mnbvcx", "rfvtgyhnuj"));
        //ExecutorService executorService = Executors.newFixedThreadPool(3);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();
        IntStream.range(0, 5).parallel().forEach(executor -> executorService.submit(similarStrings));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        long endTime = System.currentTimeMillis();
        System.out.println("total time " + (endTime - startTime));
        System.out.println(similarStrings.getGroupedStrings());
    }
}

class SimilarStrings implements Runnable {
    List<String> strings;
    static int index = 0;
    Map<Integer, List<String>> groupedStrings = new HashMap<>();

    public Map<Integer, List<String>> getGroupedStrings() {
        return groupedStrings;
    }

    SimilarStrings(List<String> strings) {
        this.strings = strings;
    }

    private int getKey(String str) {
        return IntStream.range(0, str.length())
                .parallel()
                .map(i -> 1 << Character.toLowerCase(str.charAt(i) - 'a'))
                .reduce(0, (key, currentKey) -> {
                    key |= currentKey;
                    return key;
                });
    }

    public void run() {
        while(true) {
            synchronized (this) {
                if (index >= strings.size()) return;
                int key = getKey(strings.get(index));
                groupedStrings.computeIfAbsent(key, list -> new ArrayList<>()).add(strings.get(index));
                index++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
