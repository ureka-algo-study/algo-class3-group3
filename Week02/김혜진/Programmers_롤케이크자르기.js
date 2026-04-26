const topping = [1, 2, 1, 3, 1, 4, 1, 2];

// [1, 2, 1, 3, 1, 4, 1, 2]
// [1, 2, 3, 1, 4]

// 시간 초과 오류 발생
// 모든 사이를 자르는 방법을 사용하면 slice와 set을 매번 반복생성하기 때문에 O(N^2)시간이 걸림
// 길이가 100만일 때 1조번..

// for (let i = 1; i < rollcake.length; i++) {
//   const elder = new Set(rollcake.slice(0, i));
//   const brother = new Set(rollcake.slice(i, rollcake.length));

//   if (elder.size === brother.size) count++;
// }

function solution(topping) {
  const elder = new Set();
  const brother = {};

  let answer = 0;
  let check = 0;

  // 토핑의 종류와 갯수를 brother 객체에 저장
  // check은 brother 객체에 저장된 토핑의 종류 갯수
  for (let i = 0; i < topping.length; i++) {
    if (brother[topping[i]]) {
      brother[topping[i]]++;
    } else {
      brother[topping[i]] = 1;
      check++;
    }
  }

  // brother 객체에서 토핑을 하나씩 제거하면서 elder 객체에 추가
  for (let i = 0; i < topping.length; i++) {
    // 우선 elder 객체에 토핑을 추가하고 brother 객체에서 해당 토핑의 갯수를 줄임
    elder.add(topping[i]);
    brother[topping[i]]--;

    // b객체에서 해당 토핑의 갯수가 0이 되면 check를 줄임
    if (!brother[topping[i]]) check--;
    // elder set의 크기와 check가 같으면 answer를 +1 함
    if (elder.size === check) answer++;
  }

  return answer;
}

console.log(solution(topping));

// 이 방법을 생각하는게 어려웠음
