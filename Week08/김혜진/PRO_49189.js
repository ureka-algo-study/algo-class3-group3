const n = 6;
vertex = [
  [3, 6],
  [4, 3],
  [3, 2],
  [1, 3],
  [1, 2],
  [2, 4],
  [5, 2],
];

function solution(n, vertex) {
  // 인접 리스트 생성
  const graph = Array.from({ length: n + 1 }, () => []);
  for (const [a, b] of vertex) {
    graph[a].push(b);
    graph[b].push(a);
  }

  // BFS로 거리 계산
  const distance = new Array(n + 1).fill(-1);
  distance[1] = 0;

  const queue = [1];
  let head = 0;

  while (head < queue.length) {
    const current = queue[head++];
    for (const next of graph[current]) {
      if (distance[next] === -1) {
        distance[next] = distance[current] + 1;
        queue.push(next);
      }
    }
  }

  // 최대 거리 찾기
  const maxDistance = Math.max(...distance.slice(1));

  // 최대 거리를 가진 노드 개수
  return distance.slice(1).filter((d) => d === maxDistance).length;
}
