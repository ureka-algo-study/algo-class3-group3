const fs = require('fs');
const input = fs
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './2210.txt')
  .toString()
  .trim()
  .split('\n');

const board = input.map((row) => row.trim().split(' ').map(Number));

const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];

const result = new Set();

function dfs(startX, startY) {
  // [x, y, 현재까지 만든 숫자] 를 스택에 저장
  const stack = [[startX, startY, String(board[startX][startY])]];

  while (stack.length > 0) {
    const [x, y, number] = stack.pop();

    // 6자리 완성
    if (number.length === 6) {
      result.add(number);
      continue;
    }

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

      stack.push([nx, ny, number + board[nx][ny]]);
    }
  }
}

// 모든 칸에서 시작
for (let i = 0; i < 5; i++) {
  for (let j = 0; j < 5; j++) {
    dfs(i, j);
  }
}

console.log(result.size);
