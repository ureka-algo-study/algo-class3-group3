const n = 5;
const results = [
  [4, 3],
  [4, 2],
  [3, 2],
  [1, 2],
  [2, 5],
];

function solution(n, results) {
  // win[i][j] = true → i가 j를 이김 (직접 또는 추론)
  const win = Array.from({ length: n + 1 }, () => Array(n + 1).fill(false));

  for (const [a, b] of results) {
    win[a][b] = true;
  }

  // 전이성 적용: i가 k를 이기고 k가 j를 이기면, i도 j를 이긴다
  for (let k = 1; k <= n; k++) {
    for (let i = 1; i <= n; i++) {
      for (let j = 1; j <= n; j++) {
        if (win[i][k] && win[k][j]) {
          win[i][j] = true;
        }
      }
    }
  }

  let answer = 0;
  for (let i = 1; i <= n; i++) {
    let knownCount = 0;
    for (let j = 1; j <= n; j++) {
      // i가 j를 이기거나, j가 i를 이기면 관계가 확정됨
      if (win[i][j] || win[j][i]) knownCount++;
    }
    if (knownCount === n - 1) answer++;
  }

  return answer;
}

console.log(solution(n, results));
