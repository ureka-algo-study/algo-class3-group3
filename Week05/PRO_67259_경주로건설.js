function solution(board) {
  const N = board.length;
  const INF = Infinity;

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
