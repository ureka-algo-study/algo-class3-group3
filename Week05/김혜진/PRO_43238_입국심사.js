// 처음에는 몇 명을 처리해야 최소인지 계산하려고 했는데, 그 방법은 시간 초과가 발생했다.
// 반대로 시간안에 몇 명을 처리할 수 있는지 생각했야했다. 이 방법은 이분 탐색을 이용해서 mid 시간안에 몇명을 처리할 수 있는지 계산하는 방식이다.

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
