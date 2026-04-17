const fs = require('fs');
const input = fs
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './14248.txt')
  .toString()
  .trim()
  .split('\n');

const stoneLength = Number(input[0]);
const start = Number(input[2]) - 1;

const stone = input[1].split(' ').map(Number);
let visited = Array(stone.length).fill(false);

// 초기 세팅
visited[start] = true;
let stack = [start];

function dfs() {
  while (stack.length > 0) {
    const currentValue = stack.pop();

    // 방문 하지 않았으면 방문 처리
    if (visited[currentValue] === false) {
      visited[currentValue] = true;
    }

    // 좌우 값이 유효한 값인지 확인
    const leftValue =
      currentValue - stone[currentValue] > -1
        ? currentValue - stone[currentValue]
        : undefined;
    const rightValue =
      currentValue + stone[currentValue] < stoneLength
        ? currentValue + stone[currentValue]
        : undefined;

    // console.log(leftValue, rightValue);

    // 좌우 값이 유효하고 방문하지 않았으면 추가
    if (leftValue !== undefined && visited[leftValue] === false)
      stack.push(leftValue);
    if (rightValue !== undefined && visited[rightValue] === false)
      stack.push(rightValue);
  }
}

dfs();

// 방문한 곳 찾기
const count = visited.filter((v) => v === true);
console.log(count.length);
