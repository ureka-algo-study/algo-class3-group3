function solution(n, s) {
  if (n > s) return [-1];
  const rest = s % n;
  const val = Math.floor(s / n);

  const answer = new Array(n).fill(val);

  for (let i = 0; i < rest; i++) {
    answer[i] += 1;
  }

  return answer.sort();
}
