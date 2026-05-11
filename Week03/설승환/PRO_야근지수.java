import java.util.*;

class Solution {
    long sum = 0;
    int[] arr = new int[50001];
    int max = 0;
    public long solution(int n, int[] works) {
        for (int work : works) {
            sum += work;
            arr[work]++;
            max = Math.max(work, max);
        }
        if (sum <= n) {
            return 0;
        }
        while(n > 0 && max > 0){
            if(arr[max] == 0){
                max--;
                continue;
            }
            //n이 더 큰 경우 다 뺄 수 있는 경우
            if(n >= arr[max]){
                n -= arr[max];
                arr[max - 1] += arr[max];
                arr[max] = 0;
                max--;
            }
            //n이 더 작은 경우, 즉 최대한 빼고 마는 경우
            else{
                arr[max] -= n;
                arr[max - 1] += n;
                n = 0;
            }

        }
        long answer = 0;

        for (int i = 1; i <= 50000; i++) {
            if (arr[i] > 0) {
                answer += (long) i * i * arr[i];
            }
        }

        return answer;
    }
}
