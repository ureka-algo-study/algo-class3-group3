// A, B 오름 차순 정렬
// B의 첫 값을 하나씩 제거하고나서 A와 비교 (성능을 위해 실제 제거는 안함)
// A의 끝 인덱스를 하나씩 줄여가면서, B의 인덱스를 끝에서부터 최대 승리점을 갱신 

//       i
// 1 3 5 7
// 1 2 6 6

//     i
// 1 3 5 7
// 2 6 6 -> i + 1 == 3 이므로 즉각 return

// 끝에서부터 순회
// A의 요소가 B의 요소보다 크거나 같으면, A의 인덱스 -1
// A의 요소가 B의 요소보다 작으면, B의 인덱스 -1 -> 점수 증가

//     i
// 1 3 5 7
// 1 2 2 6 

// 1 3 5 7
// 2 6 

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int maxWinScore = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int score = 0;
        int j = B.length-1;
        
        for (int i=A.length-1; i>=0; i--) {
           
            if (A[i] < B[j]) {
                score++;
                j--;
            }  
            
        }
        return score;
        
        
//         // 10^5
//         for (int i=A.length-1; i>=0; i--) {
//             int score = 0;
//             int k = B.length-1;
            
//             // 10^5
//             for (int j=i; j>=0; j--) {
//                 if (B[k--] > A[j]) {
//                     score++;
//                 }    
//             }
            
//             // A 배열의 i 구간과 B 배열의 모든 요소의 개수가 같으면 return 
//             if (i + 1 == score) return score;
//         }
//         return 0;
    }
}
