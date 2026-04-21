const fs = require('fs');
const input = fs
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './20207.txt')
  .toString()
  .trim()
  .split('\n');

const N = Number(input[0]);
const calender = [];
let size = 0;
for (let i = 0; i < N; i++) {
  const [start, end] = input[i + 1].split(' ').map(Number);
  calender.push([start, end]);
}

const countDay = Array.from({ length: 367 }, () => 0);

for (const day of calender) {
  for (let i = day[0]; i <= day[1]; i++) {
    countDay[i]++;
  }
}
// console.log(countDay);

for (let i = 0; i <= 366; i++) {
  if (countDay[i] === 0) continue;
  const keep = [];
  for (let j = i; j <= 366; j++) {
    if (countDay[j] === 0) {
      //   console.log('0 만나버림', keep);
      size += keep.length * Math.max(...keep);
      i = j;
      break;
    }
    keep.push(countDay[j]);
  }
}

console.log(size);
