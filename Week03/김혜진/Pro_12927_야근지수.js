// works에 들어있는 값들의 차가 최대한 적게 만들어야함

// static boolean canCut(int[] trees, long height, long need) {
//     // height로 잘랐을 때 얻을 수 있는 목재 계산
//     long total = 0;
//     for (int tree : trees) {
//         if (tree > height) {
//             total += tree - height;
//         }
//     }
//     return total >= need;
// }

// static long treeCutting(int[] trees, long need) {
//     long left = 0;
//     long right = Arrays.stream(trees).max().getAsInt();
//     long answer = 0;

//     while (left <= right) {
//         long mid = left + (right - left) / 2;
//         if (canCut(trees, mid, need)) {
//             answer = mid;
//             left = mid + 1;
//         } else {
//             right = mid - 1;
//         }
//     }

//     return answer;
// }

// function cuttable(works, height, need) {}

// function solution(n, works) {
//   let answer = 0;

//   works.reduce((a, b) => a + b, 0);

//   const total = works.reduce((acc, cur) => acc + cur, 0);
//   if (total >= n) return 0;

//   const leftNum = works[works.length - 1];
//   const rightNum = works[0];

//   while (leftNum <= rightNum) {
//     const mid = Math.floor((leftNum + rightNum) / 2);
//     const needed = works.reduce((acc, cur) => acc + Math.max(cur - mid, 0), 0);

//     if (needed <= n) {
//       rightNum = mid;
//     } else {
//       leftNum = mid + 1;
//     }
//   }

//   console.log(maxNum, minNum);

//   const L = leftNum;
//   const usedForL = works.reduce((acc, cur) => acc + Math.max(cur - L, 0), 0);
//   const remainder = n - usedForL;

//   const cntAtL = works.filter((w) => w >= L).length;

//   answer += remainder * (L - 1) * (L - 1);
//   answer += (cntAtL - remainder) * L * L;
//   answer += works.filter((w) => w < L).reduce((acc, cur) => acc + w * w, 0);

//   return answer;
// }

const solution = (n, works) => {
  works.sort((a, b) => b - a);
  let idx = 0;
  while (n > 0) {
    if (works[idx] <= works[0]) idx = 0;
    if (works[idx] === 0 || idx >= works.length) break;
    works[idx]--;
    n--;
    idx++;
    if (idx >= works.length) idx = 0;
  }
  console.log(works);
  return works.reduce((a, c) => a + c * c, 0);
};

console.log(solution(3, [4, 3, 3]));
