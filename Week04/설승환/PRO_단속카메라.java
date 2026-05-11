import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) ->{
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        //첫 요소 마지막 a 잡고 쭉 가다가 앞이 a보다 크면 answer++;
        int num = routes[0][1];
        int idx = 1;
        while(idx < routes.length){
            if(routes[idx][0] > num){
                answer++;
                num = routes[idx][1];
            }
            idx++;
        }
        //마지막이 따로 떨어져있지 않았던 경우
        if(num >= routes[routes.length - 1][0]){
            answer++;
        }

        return answer;
    }
}
