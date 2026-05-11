package programmers.array.NumberGame;

import java.util.*;

class Solution {
    //상대의 가장 큰 값에 우리가 이길 수 있는 경우들을 넣어놓고 가장 작은 수로 응수
    //A쫙 세우고 a와 b 10만 이하 1000만 정도로 따져야하나.
    public int solution(int[] A, int[] B) {
        //n log n
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        //n
        int flag = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = flag; j< B.length; j++){
                //값이 더 큰 경우
                if(A[i] < B[j]){
                    //j 시작 위치를 j+1로 옮김
                    flag = j + 1;
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
