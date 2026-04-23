const fs = require('fs');
const input = fs
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './1283.txt')
  .toString()
  .trim()
  .split('\n');

const N = Number(input[0]);
const functions = input.slice(1);

const functions_key = [];

// // function_key에 없을 때
// // split(" ")해서 word[1]번째 단어의 첫번째 char이 function_key에 있는지 비교
// // 1. 없으면 functions_key에 추가
// // 2. 있으면 functions[i]의 char가 있는지 없는지 확인

// // 2-1. 한덩어리 단어면 바로 2번
// // 2-2. 두덩어리 단어면 1번 진행 후 2번

// for (let i = 0; i < N; i++) {
//   // 맨 처음 char가 function_key에 없을 때
//   if (functions_key.includes(functions[i][0].toUpperCase()) === false) {
//     functions_key.push(functions[i][0].toUpperCase());
//     console.log('[' + functions[i][0] + ']' + functions[i].slice(1));
//   } else {
//     const words = functions[i].split(' ');

//     // 2
//     if (words.length === 1) {
//       checkCharInFunctionsKey(words);
//     } else {
//       const result = checkTwoPieces(words);
//       if (result === false) {
//         checkCharInFunctionsKey(words);
//       }
//     }
//   }
// }

// function checkCharInFunctionsKey(words) {
//   //   console.log('checkCharInFunctionsKey : ', words[0]);
//   for (let i = 1; i < words[0].length; i++) {
//     if (words[0][i] === ' ') continue;
//     if (!functions_key.includes(words[0][i].toUpperCase())) {
//       functions_key.push(words[0][i].toUpperCase());
//       console.log(
//         words[0].slice(0, i) +
//           '[' +
//           words[0][i] +
//           ']' +
//           words[0].slice(i + 1) +
//           (words.length > 1 ? ' ' + words.slice(1).join(' ') : ''),
//       );
//       return;
//     }
//   }
//   console.log(words[0]);
//   return;
// }

// function checkTwoPieces(words) {
//   if (!functions_key.includes(words[1][0].toUpperCase())) {
//     functions_key.push(words[1][0].toUpperCase());
//     console.log(words[0] + ' [' + words[1][0] + ']' + words[1].slice(1));
//     return true;
//   }
//   return false;
// }

for (let i = 0; i < N; i++) {
  let words = functions[i].split(' ');
  let found = false;

  // 1단계: 모든 단어의 첫 글자 확인
  for (let j = 0; j < words.length; j++) {
    let char = words[j][0].toUpperCase();
    if (!functions_key.includes(char)) {
      functions_key.push(char);
      words[j] = `[${words[j][0]}]${words[j].slice(1)}`;
      console.log(words.join(' '));
      found = true;
      break;
    }
  }

  if (found) continue;

  // 2단계: 왼쪽부터 모든 글자 확인
  let origin = functions[i];
  for (let j = 0; j < origin.length; j++) {
    if (origin[j] === ' ') continue;
    let char = origin[j].toUpperCase();
    if (!functions_key.includes(char)) {
      functions_key.push(char);
      console.log(origin.slice(0, j) + `[${origin[j]}]` + origin.slice(j + 1));
      found = true;
      break;
    }
  }

  // 3단계: 여전히 못 찾았다면 그대로 출력
  if (!found) console.log(origin);
}

// 5
// New
// Open
// Save
// Save As
// Save All
