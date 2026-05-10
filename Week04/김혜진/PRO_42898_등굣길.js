// 효율성 테스트 실패

function solution(m, n, puddles) {
  var answer = 0;
  const dp = Array.from(Array(n), () => Array(m).fill(0));

  for (let i = 0; i < puddles.length; i++) {
    const [x, y] = puddles[i];
    // 웅덩이 위치는 -1로 표시
    // 이후 계산에서 0으로 처리
    dp[y - 1][x - 1] = -1;
  }

  // 시작점 초기화
  dp[0][0] = 1;

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (dp[i][j] === -1) {
        // 웅덩이 위치는 경로가 없으므로 0으로 처리
        dp[i][j] = 0;
        continue;
      }
      // 시작점은 이미 초기화되어 있어서 제외
      if (i === 0 && j === 0) continue;

      // 최소거리를 구하기 위해서는 위쪽과 왼쪽에서 오는 경로의 수를 더하는 것이기 때문에
      // i - 1, j (위쪽)과 i, j - 1 (왼쪽)에서 오는 경로의 수를 더한다.
      dp[i][j] = ((dp[i - 1]?.[j] || 0) + (dp[i][j - 1] || 0)) % 1000000007;

      // 효율성 테스트 실패

      // m, n이 커지면 dp 값이 천문학적으로 커집니다.
      // 자바스크립트는 큰 숫자 연산이 느려지기 때문에 효율성 실패가 난다하더군요.
      //   dp[i][j] = ((dp[i - 1]?.[j] || 0) + (dp[i][j - 1] || 0));
    }
    // console.log(dp);
  }

  //   answer = dp[n - 1][m - 1] % 1000000007;

  return dp[n - 1][m - 1];
}

const m = 4;
const n = 3;
const puddles = [[2, 2]];

console.log(solution(m, n, puddles));
