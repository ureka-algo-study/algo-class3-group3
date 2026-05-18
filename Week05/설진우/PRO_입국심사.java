import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left = 1;
        
        long maxTime = 0;
        for (int time : times) {
            if (time > maxTime) maxTime = time;
        }
        long right = maxTime * n; 
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            
            for (int time : times) {
                sum += mid / time;
            }
            
            if (sum >= n) {
                answer = mid;
                right = mid - 1;
            } 
            else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
