class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hashmap =        Arrays.stream(strs).collect(Collectors.toList()).parallelStream().collect(Collectors.groupingBy(Solution::getAnagramKey));
        
        return hashmap.values().parallelStream().collect(Collectors.toList());
    }

    private static String getAnagramKey(String str) {
        System.out.println(Thread.currentThread().getName());
        char[] s = str.toCharArray();
        Arrays.sort(s);
        return String.valueOf(s);
    }
}
