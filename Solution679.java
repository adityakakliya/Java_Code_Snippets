class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> numsDouble = Arrays.stream(nums).mapToObj(i -> (double) i).collect(Collectors.toList());
        return judgePoint24 (numsDouble);
    }
    
    private boolean judgePoint24 (List<Double> nums) {
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;
        
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    List<Double> values = getValues(nums.get(i), nums.get(j));
                    List<Double> remainingList = getRemainingList(nums,i,j);
                    for (Double value : values) {
                        remainingList.add(value);
                        if (judgePoint24(remainingList)) {
                            return true;
                        }
                        remainingList.remove(remainingList.size()-1);
                    }
                }
            }
        }
        return false;
    }
    
    private List<Double> getRemainingList(List<Double> list, int i, int j) {
        List<Double> remainingList = new ArrayList<>();
        for (int k = 0; k < list.size(); k++) {
            if (k != i && k != j) remainingList.add(list.get(k));
        }
        return remainingList;
    }
    
    private List<Double> getValues(Double operand1, Double operand2) {
        List<Double> values = new ArrayList<>();
        values.add(operand1 + operand2);
        values.add(operand1 - operand2);
        values.add(operand1 * operand2);
        if (operand2 != 0) values.add(operand1 / operand2);
        return values;
    }
}
