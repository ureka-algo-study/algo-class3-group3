import java.util.*;

class Solution {
	public int solution(int[][] routes) {
		int answer = 1;

		Arrays.sort(routes, (a, b) -> {
			return a[1] - b[1];
		});

		int temp = routes[0][1];
		for(int i = 1; i < routes.length; i++) {
			if(temp < routes[i][0]) {
				answer++;
				temp = routes[i][1];
			}
		}

		return answer;
	}
}
// {-20, -15}, {-18, -13}, {-14, -5}, {-5, -3}