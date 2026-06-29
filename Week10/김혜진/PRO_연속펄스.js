function solution(sequence) {
  const n = sequence.length;

  // 두 가지 펄스 수열 적용
  const pulse1 = sequence.map((v, i) => (i % 2 === 0 ? v : -v));
  const pulse2 = sequence.map((v, i) => (i % 2 === 0 ? -v : v));

  console.log('Pulse 1:', pulse1);
  console.log('Pulse 2:', pulse2);

  // 카데인 알고리즘으로 최대 부분합 구하기
  function kadane(arr) {
    let maxSum = -Infinity;
    let current = 0;

    for (const v of arr) {
      current = Math.max(current + v, v); // 이어가기 vs 새로 시작
      maxSum = Math.max(maxSum, current);
    }

    return maxSum;
  }

  return Math.max(kadane(pulse1), kadane(pulse2));
}

console.log(solution([2, 3, -6, 1, 3, -1, 2, 4]));
