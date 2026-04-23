import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int MinGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backtracking(matrix, 0, new boolean[N], 0);
        System.out.println(MinGap);
    }

    // 방문배열로 구별: N/2개를 골라서 탐색 -> 나머지 N/2개는 자동으로 설정
    // 탐색한다/탐색안하다 선택을 분기
    private static void backtracking(int[][] map, int start, boolean[] visited, int cnt) {
        // N/2개 탐색완료시에 종료
        if (cnt == N/2) {
            int startTeam = 0;
            int linkTeam = 0;

            // i와 j가 동일한 맵의 값은 합산 안해도됨 (어차피 0임)
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    // 방문한 지점은 스타트팀
                    if (visited[i] && visited[j]) {
                        startTeam += map[i][j] + map[j][i];
                    }

                    // 방문한 지점이 아니면 링크드팀
                    if (!visited[i] && !visited[j]) {
                        linkTeam += map[i][j] + map[j][i];
                    }
                }
            }

            // 최소차이 갱신
            MinGap = Math.min(MinGap, Math.abs(linkTeam - startTeam));

            return;
        }

        // 중복 제거를 위해 방문한 지점의 다음 지점부터 탐색
        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(map, i + 1, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
