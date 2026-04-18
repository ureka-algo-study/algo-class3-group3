import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 격자 탐색
// 동일한 문자인 경우 상하좌우 이동
// 이동한 지점은 방문 표시
// 각 지점을 시작지점으로 탐색 -> 방문한 지점이 아라면 탐색 -> 카운팅
// 적녹 색약인 경우는 매트릭스에서 녹색 -> 레드로 변경하고 위의 작업 수행
public class Main {
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int normal = countGroup(new boolean[n][n], matrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 'G') matrix[i][j] = 'R'; 
            }
        }

        int redGreenNotDistinguished = countGroup(new boolean[n][n], matrix);

        System.out.println(normal + " " + redGreenNotDistinguished);

    }

    private static int countGroup(boolean[][] visited, char[][] matrix) {
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j]) {
                    dfs(matrix, i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(char[][] matrix, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 ||
                nx >= matrix.length || ny >= matrix[0].length ||
                visited[nx][ny] ||
                matrix[x][y] != matrix[nx][ny]) continue;
            dfs(matrix, nx, ny, visited);
        }
    }
}
