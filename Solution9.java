// store digits
// 121
// last dig -> 1
// 12 -> 2
// 1
// 1 2 1

// no of digits -> 10 digits -> 
// lsb base -> 1
// msb base -> 1e (no of digits - 1)
// while (msbase > lsbbase)

class Solution {
    public boolean isPalindrome(int x) {
        long msbBase = (long)1E12;
        while (x != 0 && x/msbBase == 0) {
            msbBase/=10;
        }
        while (x > 0) {
            int msbDigit = x/(int)msbBase;
            int lsbDigit = x%10;
            if (msbDigit == lsbDigit) {
                x = x %(int)msbBase;
                x/=10;
                msbBase/=100;   
            } else {
                break;
            }
        }
        return x == 0;
    }
}
