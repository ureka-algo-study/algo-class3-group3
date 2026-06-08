// 문제를 이해 못함
function solution(n, t, m, timetable) {
  // 분으로 변환
  const toMin = (time) => {
    const [h, mm] = time.split(':').map(Number);
    return h * 60 + mm;
  };

  // 분을 HH:MM으로 변환
  const toTime = (min) => {
    const h = String(Math.floor(min / 60)).padStart(2, '0');
    const mm = String(min % 60).padStart(2, '0');
    return `${h}:${mm}`;
  };

  // 크루 도착시간 정렬
  const crews = timetable.map(toMin).sort((a, b) => a - b);

  let lastShuttle = 0;
  let lastCrew = -1;
  let crewIdx = 0;

  for (let i = 0; i < n; i++) {
    const shuttle = 9 * 60 + t * i; // 셔틀 도착 시각
    lastShuttle = shuttle;

    let count = 0;
    // 이 셔틀에 탈 수 있는 크루 태우기
    while (count < m && crewIdx < crews.length && crews[crewIdx] <= shuttle) {
      lastCrew = crews[crewIdx]; // 마지막으로 탄 크루 시각 기록
      crewIdx++;
      count++;
    }

    // 마지막 셔틀이면 멈춤
    if (i === n - 1) {
      if (count < m) {
        // 자리 남음 → 셔틀 시각에 도착
        return toTime(lastShuttle);
      } else {
        // 꽉 참 → 마지막 크루보다 1분 일찍
        return toTime(lastCrew - 1);
      }
    }

    lastCrew = -1; // 다음 셔틀을 위해 초기화
  }
}
