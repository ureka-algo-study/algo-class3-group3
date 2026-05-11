import java.util.*;

class Solution {
	public int solution(int[] A, int[] B) {
		int answer = 0;

		Arrays.sort(A);
		Arrays.sort(B);

		int AIndex = 0;
		for(int i = 0; i < B.length; i++) {
			if(B[i] > A[AIndex]) {
				AIndex++;
				answer++;
			}
		}

		return answer;
	}
}
// 1 3 5 7
// 2 2 6 8