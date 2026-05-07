// const operations = ['I 16', 'I -5643', 'D -1', 'D 1', 'D 1', 'I 123', 'D -1'];
const operations = [
  'I -45',
  'I 653',
  'D 1',
  'I -642',
  'I 45',
  'I 97',
  'D 1',
  'D -1',
  'I 333',
];

function deleteMAX(arr) {
  arr.shift();
}
function deleteMIN(arr) {
  arr.pop();
}

function solution(operations) {
  var answer = [];

  for (const op of operations) {
    if (op === 'D -1') {
      deleteMIN(answer);
    } else if (op === 'D 1') {
      deleteMAX(answer);
    } else {
      const num = Number(op.split(' ')[1]);
      answer.push(num);
      answer.sort((a, b) => b - a);
      //   console.log(answer);
    }
  }
  return [answer[0] || 0, answer[answer.length - 1] || 0];
}

console.log(solution(operations));
