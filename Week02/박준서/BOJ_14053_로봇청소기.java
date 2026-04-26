import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.*;  
  
public class Main {  
  
    // 시작위치 d가 정해지므로 북동남서 움직임을 정확히 정의해야한다.  
    // 북 이동은 행으로 -1 만큼 이동하므로 (-1, 0)    // 서 이동은 열으로 -1 만큼 이동하므로 (0, -1)    // 나머지 남과 동은 맞춰짐  
    // 북 동 남 서  
    static int[] dx = {-1, 0, 1, 0};  
    static int[] dy = {0, 1, 0, -1};  
  
  
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer st = new StringTokenizer(br.readLine());  
        int n = Integer.parseInt(st.nextToken());  
        int m = Integer.parseInt(st.nextToken());  
  
        st = new StringTokenizer(br.readLine());  
        int x = Integer.parseInt(st.nextToken());  
        int y = Integer.parseInt(st.nextToken());  
        int d = Integer.parseInt(st.nextToken());  
  
        int[][] map = new int[n][m];  
        for (int i = 0; i < n; i++) {  
            st = new StringTokenizer(br.readLine());  
            for (int j = 0; j < m; j++) {  
                map[i][j] = Integer.parseInt(st.nextToken());  
            }  
        }  
  
        boolean running = true;  
  
        while (running) {  
            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.  
            if (map[x][y] == 0) {  
                map[x][y] = 2; // 청소 마킹  
            }  
  
            boolean isEmpty = false;  
  
            // 4방향 중에서 빈칸이 있는지 확인  
            for (int i = 0; i < 4; i++) {  
                int nx = x + dx[i];  
                int ny = y + dy[i];  
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 0) {  
                    isEmpty = true;  
                    break;  
                }  
            }  
  
            if (isEmpty) { // 4칸 중 청소되지 않은 빈 칸이 있는 경우  
                d = (4 + d - 1) % 4; // 반시계 방향으로 90도 회전한다.  
                int nx = x + dx[d];  
                int ny = y + dy[d];  
                // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.  
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 0) {  
                    x = nx;  
                    y = ny;  
                }  
            } else { // 4칸 중 청소되지 않은 빈 칸이 없는 경우  
                // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.  
                // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.  
                int back = (4 + d + 2) % 4; // 후진  
                int nx = x + dx[back];  
                int ny = y + dy[back];  
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {  
                    if (map[nx][ny] != 1) {  
                        x = nx;  
                        y = ny;  
                    } else {  
                        running = false;  
                    }  
                }  
            }  
        }  
  
        int cnt = 0;  
        for (int i = 0; i < n; i++) {  
            for (int j = 0; j < m; j++) {  
                if (map[i][j] == 2) cnt++;  
            }  
        }  
        System.out.println(cnt);  
    }  
}
