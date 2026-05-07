function isOneCharDiff(word1, word2) {
  let diffCount = 0;
  for (let i = 0; i < word1.length; i++) {
    if (word1[i] !== word2[i]) diffCount++;
    if (diffCount > 1) return false;
  }
  return diffCount === 1;
}

function solution(begin, target, words) {
  if (!words.includes(target)) return 0;

  const queue = [[begin, 0]];
  const visited = new Set([begin]);

  while (queue.length > 0) {
    const [current, step] = queue.shift();

    if (current === target) return step;

    for (const word of words) {
      if (isOneCharDiff(current, word) && !visited.has(word)) {
        visited.add(word);
        queue.push([word, step + 1]);
      }
    }
  }

  return 0;
}

const begin = 'hit';
const target = 'cog';
const words = ['hot', 'dot', 'dog', 'lot', 'log', 'cog'];

solution(begin, target, words);
