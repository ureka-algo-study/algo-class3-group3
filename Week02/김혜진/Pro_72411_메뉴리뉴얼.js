const orders = ['ABCFG', 'AC', 'CDE', 'ACDE', 'BCFG', 'ACDEH'];
const course = [2, 3, 4];

function solution(orders, course) {
  console.log(orders, course);
  const result = [];

  for (const size of course) {
    const countMap = new Map();
    console.log(size);
    for (const order of orders) {
      // 각 주문을 정렬 후 size 크기 조합 생성
      const sorted = order.split('').sort();
      getCombinations(sorted, size).forEach((combo) => {
        const key = combo.join('');
        countMap.set(key, (countMap.get(key) || 0) + 1);
      });
    }

    // 해당 size에서 최대 주문 횟수
    const max = Math.max(...countMap.values());

    // 최대값이 2 이상인 것만 추가
    if (max >= 2) {
      countMap.forEach((count, menu) => {
        if (count === max) result.push(menu);
      });
    }
  }

  return result.sort();
}

function getCombinations(arr, size) {
  if (size === 1) return arr.map((v) => [v]);
  const result = [];
  for (let i = 0; i <= arr.length - size; i++) {
    const rest = getCombinations(arr.slice(i + 1), size - 1);
    rest.forEach((combo) => result.push([arr[i], ...combo]));
  }
  return result;
}
