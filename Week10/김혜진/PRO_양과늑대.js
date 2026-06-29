function solution(n, costs) {
  const visited = new Array(n).fill(false);

  // 인접 리스트 구성 (양방향)
  const graph = Array.from({ length: n }, () => []);
  for (const [a, b, cost] of costs) {
    graph[a].push([b, cost]);
    graph[b].push([a, cost]);
  }

  visited[0] = true; // 0번 섬에서 시작
  let visitedCount = 1;
  let total = 0;

  while (visitedCount < n) {
    let minCost = Infinity;
    let nextNode = -1;

    // 방문한 섬들에서 갈 수 있는 가장 저렴한 다리 탐색
    for (let i = 0; i < n; i++) {
      if (!visited[i]) continue;
      for (const [next, cost] of graph[i]) {
        if (!visited[next] && cost < minCost) {
          minCost = cost;
          nextNode = next;
        }
      }
    }

    visited[nextNode] = true;
    total += minCost;
    visitedCount++;
  }

  return total;
}
