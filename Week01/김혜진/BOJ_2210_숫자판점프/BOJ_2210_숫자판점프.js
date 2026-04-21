// const fs = require('fs');
// const input = fs
//   .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './2210.txt')
//   .toString()
//   .trim()
//   .split('\n');

// const board = input.map((row) => row.trim().split(' ').map(Number));

//            하  상   우  좌
// const dx = [1, -1, 0, 0];
// const dy = [0, 0, 1, -1];

// const result = new Set();

// function dfs(startX, startY) {
//   // [x, y, 현재까지 만든 숫자] 를 스택에 저장
//   const stack = [[startX, startY, String(board[startX][startY])]];

//   while (stack.length > 0) {
//     const [x, y, number] = stack.pop();

//     // 6자리 완성
//     if (number.length === 6) {
//       result.add(number);
//       continue;
//     }

//     for (let i = 0; i < 4; i++) {
//       const nx = x + dx[i];
//       const ny = y + dy[i];

//       // 유효한 범위가 아니면 패스
//       if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

//       stack.push([nx, ny, number + board[nx][ny]]);
//     }
//   }
// }

// // 모든 칸에서 시작
// for (let i = 0; i < 5; i++) {
//   for (let j = 0; j < 5; j++) {
//     dfs(i, j);
//   }
// }

// console.log(result.size);

// 풀이 과정을 생각하고 나니 지금 코드는 테스트 값같이 같은 숫자가 많으면 같은 수를 여러번 순회하는게 비효율적이라고 생각되서 좀 더 나은 방법을 생각했습니다.
// hash테이블을 사용하는 set를 사용하면 같은 값을 빠르게 사전에 제거할 수 있고, dp를 사용해서 각 칸에서 6자리 숫자를 만들 수 있는
// 경우의 수를 저장하면 중복된 숫자를 제거할 수 있을 것 같았습니다.
// 마치 3차원 배열

const fs = require('fs');
const input = fs
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './2210.txt')
  .toString()
  .trim()
  .split('\n');

const board = input.map((row) => row.trim().split(' ').map(Number));

//         하   상  우  좌
const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];

const row = board.length;
const col = board[0].length;

// 각 칸의 시작값으로 초기화
// set을 인덱스로 찾을 수 있도록 Array로 만듦
let dp = Array.from({ length: row }, (_, x) =>
  Array.from({ length: col }, (_, y) => new Set([String(board[x][y])])),
);

// ↑ 같은 방법, 더 세련된 방법

// let dp = [];

// for (let x = 0; x < row; x++) {
//     let rowArray = [];
//     for (let y = 0; y < col; y++) {
//         let char = String(board[x][y]);
//         let cellSet = new Set([char]);
//         rowArray.push(cellSet);
//     }
//     dp.push(rowArray);
// }

console.log('dp : ', dp);

// 이웃 칸으로 전파
// set 사용 이유 : 중복된 문자열이 생길 수 있기 때문에 중복 제거를 위해 set 사용
for (let step = 1; step < 6; step++) {
  const next = Array.from({ length: row }, () =>
    Array.from({ length: col }, () => new Set()),
  );

  console.log('next : ', next);

  // board의 모든 칸 탐색
  for (let x = 0; x < row; x++) {
    for (let y = 0; y < col; y++) {
      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];

        // 현재 칸에서 유효한 값이 없으면 패스
        if (nx < 0 || nx >= row || ny < 0 || ny >= col) continue;

        // nx, ny 방향의 문자를 next에 추가
        // ex) dp[x][y] = {'1'} 하상우좌가 모두 유효해서 '1', '1', '2', '2'가 추가되게 되면
        // next[nx][ny] = {'11', '11', '12', '12'} 가 되는데 set이기 때문에 중복된 값은 제거되어 {'11', '12'}가 됨
        // ex) dp[x][y] = {'11', '12'} , board[nx][ny] = '2' => next[nx][ny] = {'112', '122'}
        for (const str of dp[x][y]) {
          next[nx][ny].add(str + board[nx][ny]);
        }
      }
    }
  }

  // 끝나면 냅다 dp에 덮어씌우기
  dp = next;
  console.log('dp : ', dp);
}

// 6자리 완성된 문자열 수집
const result = new Set();

for (let x = 0; x < row; x++) {
  for (let y = 0; y < col; y++) {
    for (const str of dp[x][y]) {
      // console.log('str : ', str);
      result.add(str);
    }
  }
}
console.log('result : ', result);

console.log(result.size);
// set하나만 써도 될것같다.
