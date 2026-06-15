const user_id = ['frodo', 'fradi', 'crodo', 'abc123', 'frodoc'];
const banned_id = ['fr*d*', 'abc1**'];

function solution(user_id, banned_id) {
  const match = (uid, bid) => {
    if (uid.length !== bid.length) return false;
    for (let i = 0; i < uid.length; i++) {
      if (bid[i] !== '*' && uid[i] !== bid[i]) return false;
    }
    return true;
  };

  const candidates = banned_id.map((bid) =>
    user_id.filter((uid) => match(uid, bid)),
  );

  // console.log('candidates : ', candidates);

  const answer = new Set();

  const dfs = (idx, used, selected) => {
    if (idx === banned_id.length) {
      // 순서 무관 비교를 위해 정렬 후 join
      const key = [...selected].sort().join(',');
      // console.log('key : ', key);
      answer.add(key);
      // console.log('answer : ', answer);
      return;
    }
    for (const cand of candidates[idx]) {
      if (!used.has(cand)) {
        used.add(cand);
        selected.push(cand);
        dfs(idx + 1, used, selected);
        selected.pop();
        used.delete(cand);
      }
    }
  };

  dfs(0, new Set(), []);
  return answer.size;
}

console.log(solution(user_id, banned_id));
