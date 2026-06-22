const tickets = [
  ['ICN', 'SFO'],
  ['ICN', 'ATL'],
  ['SFO', 'ATL'],
  ['ATL', 'ICN'],
  ['ATL', 'SFO'],
];

function solution(tickets) {
  var answer = [];
  const graph = {};
  for (const [from, to] of tickets) {
    if (!graph[from]) {
      graph[from] = [];
    }
    graph[from].push(to);
    graph[from].sort();
  }

  const keep = ['ICN'];

  while (keep.length) {
    const current = keep[keep.length - 1];
    if (graph[current] && graph[current].length > 0) {
      keep.push(graph[current].shift());
    } else {
      answer.unshift(keep.pop());
    }
  }

  //   console.log(answer);
  return answer;
}

console.log(solution(tickets));
