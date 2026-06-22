const n = 5;
const roads = [
  [1, 2],
  [1, 4],
  [2, 4],
  [2, 5],
  [4, 5],
];
sources = [1, 3, 5];
destination = 5;

function solution(n, roads, sources, destination) {
  const graph = Array.from({ length: n + 1 }, () => []);
  for (const [from, to] of roads) {
    graph[from].push(to);
    graph[to].push(from);
  }

  const dist = Array(n + 1).fill(-1);

  const queue = [destination];
  let head = 0;

  dist[destination] = 0;

  while (head < queue.length) {
    const cur = queue[head++];

    for (const next of graph[cur]) {
      if (dist[next] !== -1) continue;

      dist[next] = dist[cur] + 1;
      queue.push(next);
    }
  }

  return sources.map((source) => dist[source]);
}

console.log(solution(n, roads, sources, destination));
