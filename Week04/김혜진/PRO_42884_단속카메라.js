function solution(routes) {
  var answer = 1;

  routes.sort((a, b) => a[1] - b[1]);
  let camera = routes[0][1];

  for (let i = 1; i < routes.length; i++) {
    if (routes[i][0] > camera) {
      answer++;
      camera = routes[i][1];
    }
  }

  //   console.log(routes);
  return answer;
}

const routes = [
  [-20, -15],
  [-14, -5],
  [-18, -13],
  [-5, -3],
];

// 지점:  -20  -18  -15  -14  -13  -5  -3

// 차량1:  [=========-15========]          (-20 ~ -15)
// 차량3:       [=====-13=====]            (-18 ~ -13)
// 차량2:              [==========-5====]  (-14 ~ -5)
// 차량4:                          [==-3] (-5  ~ -3)

console.log(solution(routes));
