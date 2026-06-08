package programmers;

public class BalloonExploding {
    public int solution(int[] a) {
        int n = a.length;

        if (n <= 2)
            return n;

        int[] rightMin = new int[n];
        rightMin[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        int answer = 2;

        int currentLeftMin = a[0];

        for (int i = 1; i < n - 1; i++) {
            if (a[i] > currentLeftMin && a[i] > rightMin[i + 1]) {
            } else {
                answer++;
            }

            currentLeftMin = Math.min(currentLeftMin, a[i]);
        }

        return answer;
    }
}
