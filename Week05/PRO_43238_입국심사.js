function solution(n, times) {
  times.sort((a, b) => a - b);
  let left = 1;
  let right = times[0] * n;
  let answer = right;

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);

    const total = times.reduce((acc, t) => acc + Math.floor(mid / t), 0);
    if (total >= n) {
      answer = mid;
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }
  return answer;
}
