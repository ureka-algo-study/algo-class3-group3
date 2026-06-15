package programmers;

public class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] wins = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            wins[winner][loser] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (wins[i][k] && wins[k][j]) {
                        wins[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int knownMatchCount = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    continue;

                if (wins[i][j] || wins[j][i]) {
                    knownMatchCount++;
                }
            }

            if (knownMatchCount == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
