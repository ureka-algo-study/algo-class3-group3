package programmers;

import java.util.Arrays;

public class Solution {
    public int solution(int[][] scores) {
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int maxPeerScore = 0;
        int rank = 1;

        for (int[] score : scores) {
            int currentA = score[0];
            int currentB = score[1];

            if (currentB < maxPeerScore) {
                if (currentA == wanhoA && currentB == wanhoB) {
                    return -1;
                }
            } else {
                maxPeerScore = Math.max(maxPeerScore, currentB);

                if (currentA + currentB > wanhoSum) {
                    rank++;
                }
            }
        }

        return rank;
    }
}
