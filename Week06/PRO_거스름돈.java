// 5원을 만드는 경우의 수
// 1원을 만드는 경우의 수 + 4원을 만드는 경우의 수
// 2원을 만드는 경우의 수 + 3원을 만드는 경우의 수
// 3원을 만드는 경우의 수 + 2원을 만드는 경우의 수
// 이러한 패턴은 특정 동전을 만드는 방법은 이전의 값에 대한 동전을 만드는 방법값을 사용할수있다.

// 거슬러야되는 돈: 0 ~ n까지 반복 
// dp[거슬러야되는 돈] = dp[거슬러야되는 돈] + dp[거슬러야되는 돈 - (0...거슬러야되는 돈)]
// 반환값: 거슬러야되는 돈이 n일때의 경우의 수 d[n]
class Solution {
    public int solution(int n, int[] money) {
        final int MOD = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int coin : money) {
            for (int price = coin; price <= n; price++) {
                dp[price] = (dp[price] + dp[price - coin]) % MOD;
            }
        }

        return dp[n];
    }
}
