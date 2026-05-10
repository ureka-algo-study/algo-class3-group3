// 효율성 실패
function solution(A, B) {
  var answer = 0;
  const used = Array.from({ length: B.length }, () => false);
  B.sort((a, b) => a - b);

  // A보다 조금 더 큰 값을 찾는 방법
  for (let a = 0; a < A.length; a++) {
    let min = -1;
    for (let b = 0; b < B.length; b++) {
      if (B[b] > A[a] && !used[b]) {
        min = b;
        break;
      }
    }
    // A보다 큰 값을 찾았을 때
    if (min !== -1) {
      used[min] = true;
      answer++;
    }
    // 못 찾았을 때
    else {
      for (let j = 0; j < B.length; j++) {
        if (!used[j]) {
          used[j] = true;
          break;
        }
      }
    }
  }

  return answer;
}

// 투 포인터 O(n log n)
function sol(A, B) {
  A.sort((a, b) => a - b);
  B.sort((a, b) => a - b);

  let answer = 0;
  let a = 0; // A 포인터

  for (let b = 0; b < B.length; b++) {
    if (B[b] > A[a]) {
      answer++;
      a++;
    }
    // B[b] <= A[a]: B[b]로 아무도 못 이기므로 버리고 다음 B로
  }

  return answer;
}

const A = [5, 1, 3, 7];
const B = [2, 2, 6, 8];

console.log(solution(A, B));
console.log(sol(A, B));
