const fs = require('fs');
const input = fs.readFileSync('./11724.txt').toString().trim().split('\n');
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// let graph = [];
// let stack = [];
// let count = 0;

// // N: 정점의 개수, M: 간선의 개수
// const [, M] = input[0].split(' ');
// for (let i = 1; i <= M; i++) {
//   const [u, v] = input[i].split(' ').map(Number);
//   graph.push([u, v]);
// }

// stack.push(graph[0]);

// while (stack.length > 0 && graph.length > 0) {
//   const currentValue = stack.pop();
//   const [u, v] = currentValue;

//   // graph에서 [u, v] 빼기
//   graph = graph.filter((route) => !(route[0] === u && route[1] === v));

//   if (graph.length === 0) {
//     count++;
//     break;
//   }

//   // 그래프 검사
//   for (i = 0; i < graph.length; i++) {
//     // graph에 u나 v가 있을 때
//     if (graph[i].includes(u) || graph.includes(v)) {
//       // stack에 값 추가
//       stack.push(graph[i]);
//       // graph에서 u나 v가 포함된 값 없애기
//       graph = graph.filter(
//         (route) => !(route.includes(u) || route.includes(v)),
//       );
//     }
//   }

//   // stack에 값은 없지만 graph에 값이 남아있을 때 count +1하고 stack에 graph[0] 추가
//   if (stack.length === 0 && graph.length > 0) {
//     count++;
//     const newCurrentValue = graph[0];
//     const [nu, nv] = newCurrentValue;
//     stack.push(graph[0]);
//     graph = graph.filter((route) => !(route[0] === nu && route[1] === nv));
//   }
// }

// ===================================================
// 맞는 방법
// ===================================================

let count = 0;
const [N, M] = input[0].split(' ').map(Number);

// 인접 리스트
const graph = Array.from({ length: N + 1 }, () => []);

// 방문 배열
const visited = Array(N + 1).fill(false);

// 양방향 간선 넣기
for (let i = 1; i <= M; i++) {
  const [u, v] = input[i].split(' ').map(Number);
  graph[u].push(v);
  graph[v].push(u);
}

// console.log('graph : ', graph);
// console.log('visited : ', visited);

// dfs 함수 정의
function dfs(start) {
  const stack = [start];

  while (stack.length > 0) {
    const node = stack.pop();

    // 이미 방문한 노드면 건너뜀
    if (visited[node]) continue;

    visited[node] = true;

    // 연결된 노드 중 방문 안 한 것만 stack에 추가
    for (const next of graph[node]) {
      if (!visited[next]) {
        stack.push(next);
      }
    }
  }
}

// 모든 노드 순회
for (let i = 1; i <= N; i++) {
  if (!visited[i]) {
    dfs(i);
    count++;
  }
}

console.log(count);
