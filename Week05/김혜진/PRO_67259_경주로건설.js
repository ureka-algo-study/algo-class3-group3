// DFS, BFS인 것 같은데 방향을 어떻게 다룰지 모르겠어서 시간내로 못 푼 문제
// queue에 방향과 비용도 다 같이 넣어서 BFS로 풀면 되는 문제
// N이 25이하여서 BFS가 가능했는데 더 컸으면 다익스트라로 풀어야 했을듯

// 우선 순위 큐

function solution(board) {
  const N = board.length;
  const INF = Infinity; // 어떤 수보다도 항상 크다

  const dist = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => Array(4).fill(INF)),
  );

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  const queue = [];

  queue.push([0, 0, 1, 0]);
  queue.push([0, 0, 3, 0]);
  dist[0][0][1] = 0;
  dist[0][0][3] = 0;

  let i = 0;
  while (i < queue.length) {
    const [x, y, dir, cost] = queue[i++];

    for (let d = 0; d < 4; d++) {
      const nx = x + dx[d];
      const ny = y + dy[d];

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
      if (board[nx][ny] === 1) continue;

      const nextCost = cost + 100 + (dir !== d ? 500 : 0);

      if (nextCost < dist[nx][ny][d]) {
        dist[nx][ny][d] = nextCost;
        queue.push([nx, ny, d, nextCost]);
      }
    }
  }

  return Math.min(...dist[N - 1][N - 1]);
}
