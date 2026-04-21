import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 문제 조건 모두 잘읽어야함
// 인벤터리에 가져오기: +1
// 인벤터리에 넣기: +2
// 완전 탐색: 땅의 최소 높이 ~ 최대 높이 범위 내에서 모두 계산
// 땅의 높이를 순회하며 기준 높이로 잡는다. (땅 높이를 순회할때마다 시간에 대한 최소 값 갱신)
// 맵 높이 - 기준 높이 > 0, 인벤터리++, | 맵 높이 - 기준 높이 |
// 맵 높이 - 기준 높이 < 0,, 인벤터리--, | 맵 높이 - 기준 높이 | x 2
// 답이 여러 개 있다면 그중에서 땅의 높이가 가장 높은 것 출력
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int minHeight = 256;
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        List<int[]> result = new ArrayList<>();

        for (int height = minHeight; height <= maxHeight; height++) {
            int inven = B;
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > height) {
                        int diff = map[i][j] - height;
                        inven += diff;
                        time += 2 * diff;
                    } else if (map[i][j] < height) {
                        int diff = height - map[i][j];
                        inven -= diff;
                        time += diff;
                    }
                }
            }
            if (inven >= 0) result.add(new int[]{time, height});
        }

        // time 동일하면 높이 높은 순으로 저장
        result.sort((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int minTime = result.get(0)[0];
        int maxHeightWhenMinTime = result.get(0)[1];

        System.out.println(minTime + " " + maxHeightWhenMinTime);
    }
}
