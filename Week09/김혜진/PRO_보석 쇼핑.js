const gems = [
  'DIA',
  'RUBY',
  'RUBY',
  'DIA',
  'DIA',
  'EMERALD',
  'SAPPHIRE',
  'DIA',
];

function solution(gems) {
  const gemsList = new Set(gems).size;
  const map = new Map();

  let left = 0;
  let answer = [0, gems.length - 1];

  for (let right = 0; right < gems.length; right++) {
    const gem = gems[right];

    map.set(gem, (map.get(gem) || 0) + 1);

    while (map.size === gemsList) {
      if (right - left < answer[1] - answer[0]) {
        answer = [left, right];
      }

      const leftGem = gems[left];

      map.set(leftGem, map.get(leftGem) - 1);

      if (map.get(leftGem) === 0) {
        map.delete(leftGem);
      }

      left++;
    }
  }

  return [answer[0] + 1, answer[1] + 1];
}

console.log(solution(gems));
