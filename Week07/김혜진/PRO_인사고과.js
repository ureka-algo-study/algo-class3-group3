const scores = [
  [2, 2],
  [1, 4],
  [3, 2],
  [3, 2],
  [2, 1],
];

function solution(scores) {
  if (scores.length === 1) return 1;

  // 완호꺼
  let owner = scores[0];
  let ownerSum = owner[0] + owner[1];

  // 완호 유효성 검사
  for (const [a, b] of scores) {
    if (a > owner[0] && b > owner[1]) {
      return -1;
    }
  }

  // 0번째는 내림차순으로 1번째는 올림차순으로 정렬
  let sorted = [...scores].sort((a, b) => {
    if (a[0] === b[0]) return a[1] - b[1];
    return b[0] - a[0];
  });

  //   console.log(sorted);

  let answer = 1;
  let ok = [];
  let maxB = 0;

  for (const [a, b] of sorted) {
    if (maxB <= b) {
      maxB = b;
      ok.push(a + b);
    }
  }

  for (const sum of ok) {
    if (sum > ownerSum) answer++;
  }

  return answer;
}

console.log(solution(scores));
