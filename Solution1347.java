class Solution {
    public int minSteps(String s, String t) {
        int[] freq = new int[26];

        for(char c: t.toCharArray()) {
            freq[c-'a']++;
        }

        for(char c: s.toCharArray()) {
            if(freq[c-'a'] > 0) freq[c-'a']--;
        }

        int ans = 0;
        for(int i=0;i<26;i++) {
            ans += freq[i];
        }

        return ans;
    }
}