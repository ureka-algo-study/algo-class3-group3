const sticker = [14, 6, 5, 11, 3, 9, 2, 10];
function solution(sticker) {
  if (sticker.length === 1) return sticker[0];

  // 경우 1: 첫 번째 포함 (0 ~ n-2)
  const dp1 = new Array(sticker.length).fill(0);
  dp1[0] = sticker[0];
  dp1[1] = Math.max(sticker[0], sticker[1]);
  console.log(dp1);
  for (let i = 2; i < sticker.length - 1; i++) {
    dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
    console.log(i, '번째', dp1);
  }
  console.log('==========================');
  // 경우 2: 첫 번째 제외 (1 ~ n-1)
  const dp2 = new Array(sticker.length).fill(0);
  dp2[1] = sticker[1];
  dp2[2] = Math.max(sticker[1], sticker[2]);
  console.log(dp2);
  for (let i = 3; i < sticker.length; i++) {
    dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
    console.log(i, '번째', dp2);
  }

  return Math.max(dp1[sticker.length - 2], dp2[sticker.length - 1]);
}

console.log(solution(sticker));
