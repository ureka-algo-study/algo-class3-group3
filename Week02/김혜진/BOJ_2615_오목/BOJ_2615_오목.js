const fs = require('fs');
const input = fs
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './2615.txt')
  .toString()
  .trim()
  .split('\n');

const board = input.map((line) => line.split(' '));
const visited = Array.from({ length: 19 }, () => Array(19).fill(false));
// console.log(board);
// 우, 우아래, 아래, 좌아래, 좌, 좌위, 위, 우위
const dx = [0, 1, 1, 1, 0, -1, -1, -1];
const dy = [1, 1, 0, 1, -1, 1, 0, -1];

// 다시 풀기
