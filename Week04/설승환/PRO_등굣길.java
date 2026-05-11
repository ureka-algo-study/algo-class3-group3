package programmers.implement.gotoschool;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;
        int[][] dp = new int[n + 1][m + 1];

        // 웅덩이 위치를 -1로 표시하여 구분
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        // 시작점 설정
        dp[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 시작점이거나 웅덩이인 경우 건너뜀
                if (dp[i][j] == -1) {
                    dp[i][j] = 0; // 웅덩이는 더 이상 길을 더할 수 없게 0으로 초기화
                    continue;
                }

                // 위쪽(i-1)에서 오는 경로와 왼쪽(j-1)에서 오는 경로를 더함
                // dp[1][1]은 위에서 걸러지므로 더해지지 않음
                if (i > 1) dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                if (j > 1) dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
            }
        }

        return dp[n][m];
    }
}
