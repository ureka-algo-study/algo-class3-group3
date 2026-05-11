class Solution {
	public int solution(int m, int n, int[][] puddles) {

		boolean[][] arr = new boolean[n + 1][m + 1];
		for(int i = 0; i < puddles.length; i++) {
			arr[puddles[i][1]][puddles[i][0]] = true;
		}

		int[][] dp = new int[n + 1][m + 1];
		dp[0][1] = 1;

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j]) continue;

				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
			}
		}

		return dp[n][m];
	}
}