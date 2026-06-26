class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        long currentSumA = 0;
        long currentSumB = 0;
        
        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i < sequence.length; i++) {
            long pulseA_val = (i % 2 == 0) ? sequence[i] : -sequence[i];
            long pulseB_val = (i % 2 == 0) ? -sequence[i] : sequence[i];

            currentSumA += pulseA_val;
            currentSumB += pulseB_val;

            maxSum = Math.max(maxSum, Math.max(currentSumA, currentSumB));

            if (currentSumA < 0) {
                currentSumA = 0;
            }
            if (currentSumB < 0) {
                currentSumB = 0;
            }
        }

        answer = maxSum;
        return answer;
    }
}
