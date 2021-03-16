class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        SameConsecutiveDiffernce instance = new SameConsecutiveDiffernce(n,k);
        instance.findNumbers(0, 0);
        return instance.list.stream().mapToInt(i->i).toArray();
    }
}

class SameConsecutiveDiffernce {
    int n,k;
    List<Integer> list;
    SameConsecutiveDiffernce (int n, int k) {
        this.n = n;
        this.k = k;
        list = new ArrayList<>();
    }
    
    public void findNumbers(int numberOfDigits, int currentNum) {
        //System.out.println(currentNum + " digits - " + numberOfDigits);
        if (numberOfDigits == 0) {
            IntStream.range(1, 10)
                .forEach(num -> findNumbers(numberOfDigits + 1, num));
        } else if (numberOfDigits == n) {
            list.add(currentNum);
        } else {
            int lastDigit = currentNum % 10;
            if (lastDigit + k <= 9) {
                findNumbers(numberOfDigits+1, currentNum * 10 + (lastDigit + k));                
            }
            if (k != 0 && lastDigit - k >= 0) {
                findNumbers(numberOfDigits+1, currentNum * 10 + (lastDigit - k));                
            }
        }
    }
    
}
