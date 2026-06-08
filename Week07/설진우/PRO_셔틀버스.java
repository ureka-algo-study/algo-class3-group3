package programmers;

import java.util.PriorityQueue;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crewQueue = new PriorityQueue<>();
        for (String time : timetable) {
            String[] split = time.split(":");
            int minutes = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            crewQueue.add(minutes);
        }

        int shuttleTime = 9 * 60;
        int lastBoardTime = 0;
        int boardedCount = 0;

        for (int i = 0; i < n; i++) {
            boardedCount = 0;

            while (!crewQueue.isEmpty() && crewQueue.peek() <= shuttleTime && boardedCount < m) {
                lastBoardTime = crewQueue.poll();
                boardedCount++;
            }

            if (i < n - 1) {
                shuttleTime += t;
            }
        }

        int conTime = 0;

        if (boardedCount < m) {
            conTime = shuttleTime;
        }
        else {
            conTime = lastBoardTime - 1;
        }

        return String.format("%02d:%02d", conTime / 60, conTime % 60);
    }
}
