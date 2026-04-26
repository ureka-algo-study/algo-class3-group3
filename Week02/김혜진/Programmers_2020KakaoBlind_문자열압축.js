const s = [
  'aabbaccc',
  'ababcdcdababcdcd',
  'abcabcdede',
  'abcabcabcabcdededededede',
  'xababcdcdababcdcd',
];

for (const w of s) {
  solution(w);
}

function solution(s) {
  let answer = s.length;
  let compression = '';
  let unit = 1;
  let cut = [];

  while (unit <= Math.floor(s.length / 2)) {
    for (let i = 0; i < s.length; i += unit) {
      cut.push(s.substring(i, i + unit));
    }

    for (let i = 0; i < cut.length; i++) {
      let pointer = i;
      while (pointer < cut.length) {
        if (cut[i] === cut[pointer + 1]) {
          pointer++;
        } else {
          compression += pointer - i === 0 ? cut[i] : pointer - i + 1 + cut[i];
          i = pointer;
          break;
        }
      }
    }

    answer = Math.min(compression.length, answer);
    compression = '';
    cut.length = 0;
    unit++;
  }
  console.log(answer);
  return answer;
}
