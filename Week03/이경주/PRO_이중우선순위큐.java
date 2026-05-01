import java.util.*;

class Solution {
	public int[] solution(String[] operations) {
		int[] answer = new int[2];

		PriorityQueue<Integer> minq = new PriorityQueue<>();
		PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());

		for(int i = 0; i < operations.length; i++) {
			String[] temp = operations[i].split(" ");
			String oper = temp[0];
			int num = Integer.parseInt(temp[1]);

			if(oper.equals("I")) {
				minq.offer(num);
				maxq.offer(num);
			} else if(!minq.isEmpty()) {
				if(num == 1) {
					int max = maxq.poll();
					minq.remove(max);
				} else {
					int min = minq.poll();
					maxq.remove(min);
				}
			}
		}

		if(minq.isEmpty()) {
			return new int[]{0, 0};
		}
		answer[0] = maxq.peek();
		answer[1] = minq.peek();

		return answer;
	}
}