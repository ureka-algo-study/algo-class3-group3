import java.util.*;

class Solution {
	public long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;

		Arrays.sort(times);
		long min = 0;
		long max = (long)times[times.length - 1] * n;

		long mid = 0;
		while(min <= max) {
			mid = (min + max) / 2;

			long temp = 0;
			for(int i = 0; i < times.length; i++) {
				temp += mid / times[i];
			}

			if(temp >= n) {
				answer = Math.min(answer, mid);
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}

		return answer;
	}
}