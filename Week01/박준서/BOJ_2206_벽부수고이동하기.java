import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        // 특정 지점에 벽을 부셔서 도착한 것과 벽을 부수지 않고 도착한 것은 다른 경로이다.
        // 이때, 벽을 부수며 탐색한 지점에 도착해서 이미 방문 여부를 결정해버리면, 추후 벽을 부수지 않고 해당 지점을 탐색 못하게된다.
        // 따라서 벽을 부순 유무를 방문 배열에 따로 저장이 필요하다.
        // 이를 위해 각 지점마다 벽을 부셔서 방문했는지 확인하도록 3차원 방문 배열을 사용하면 구분가능하다.
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 1}); // 행, 열, 벽 부순 유무, 거리
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int isBroken = cur[2]; // 해당 경로에서 벽을 부술수있는지 확인하기 위해 이전까지 부순 유무를 저장
            int dist = cur[3];

            // 도착
            if (x == n - 1 && y == m - 1) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 빈 칸이면 방문 햇던 적이 없는 경우시 이동
                if (map[nx][ny] == 0 && !visited[nx][ny][isBroken]) {
                    visited[nx][ny][isBroken] = true;
                    queue.offer(new int[]{nx, ny, isBroken, dist + 1});
                }
                // 벽이면 아직 한번도 안 부쉈을시 부수고 이동
                else if (map[nx][ny] == 1 && isBroken == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    queue.offer(new int[]{nx, ny, 1, dist + 1});
                }
            }
        }

        return -1; // (N, M)도달 불가
    }
}
