import java.util.*;

class Solution {
	public long solution(int n, int[] works) {
		long answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for(int i = 0; i < works.length; i++) {
			pq.offer(works[i]);
		}

		for(int i = 0; i < n; i++) {
			pq.offer(pq.poll() - 1);
		}

		if(pq.peek() <= 0) return 0;

		for(int i : pq) {
			answer += i * i;
		}

		return answer;
	}
}