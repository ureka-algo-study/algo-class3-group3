import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            pq.offer(work);
        }
        
        while (n > 0) {
            int maxWork = pq.poll();
            
            if (maxWork == 0) break;
            
            pq.offer(maxWork - 1);
            n--;
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            long work = pq.poll();
            answer += work * work;
        }
        
        return answer;
    }
}
