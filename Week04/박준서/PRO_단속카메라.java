// 예시 1
// -20,-15 -> 도착지점 -15로 지정
// -18,-13 -> 패스
// -14,-5 -> 카메라의 설치 지점보다 차량의 도착지점이 크므로 갱신 -5
// -5,-3 -> 패스

// 예시 2
// -18,-13 -> 도착지점 -13로 지정
// -14,-5 -> 카메라의 설치 지점보다 차량의 도착지점이 크므로 갱신 -5
// -5,-3 -> 패스
// -20,20 -> 패스

// 1. 두번째 요소 기준으로 오름차순 정렬
// (1) 출발지점보다 도착지점이 큰것 보장
// (2) 예시 2번의 마지막 차량처럼 출발지점이 가장 작아도 도착지점이 가장 크다면 카메리 설치 지점 고려 대상에서 가장 멀다.
// (3) 카메라의 설치 지점을 도착지점 기준으로 설정한다.

// 2. 카메라 설치 
// 카메라 초기 설치 지점: 첫번째 차량의 도착지점
// 카메라의 설치 지점이 다음 차량의 출발지 보다 같거나 작으면 패스, 크면 차량의 도착지점으로 갱신

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        int carmeraCnt = 1;
        int carmeraPoint = routes[0][1];
        for (int i=1; i<routes.length; i++) {
            if (carmeraPoint < routes[i][0]) {
                carmeraPoint = routes[i][1];
                carmeraCnt++;
            }
        }
        return carmeraCnt;
    }
}
