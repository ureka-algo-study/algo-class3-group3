import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11724_연결요소의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] list = new ArrayList[n+1]; // 7
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 간선 연결
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        boolean[] visited = new boolean[n+1];
        int cnt = 0;
        // DFS로 탐색한 그래프(연결된 노드)의 개수 카운팅
        for (int i = 1; i <= n; i++) {
            if (dfs(list, i, visited, 0)) cnt++;
        }
        System.out.println(cnt);
    }

    // 방문했던 노드가 1개라도 있으면 true 반환, 없으면 false 반환
    private static boolean dfs(List<Integer>[] list, int start, boolean[] visited, int cnt) {
        if (visited[start]) {
            return cnt > 0;
        }

        visited[start] = true;

        // 해당 정점의 인접 정점 이동
        for (int i = 0; i < list[start].size(); i++) {
            int next = list[start].get(i);
            dfs(list, next, visited, cnt + 1);
        }

        return true;
    }
}
