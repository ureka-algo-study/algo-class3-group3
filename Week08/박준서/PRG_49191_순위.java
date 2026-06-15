import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        // win/lose 그래프를 인접 리스트로 생성
        List<Integer>[] winGraph = new ArrayList[n+1];
        List<Integer>[] loseGraph = new ArrayList[n+1];
        
        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }
        
        // 주어진 배열로부터 win/lose 그래프 값 저장
        for (int[] result: results) {
            int a = result[0];
            int b = result[1];
            winGraph[a].add(b);
            loseGraph[b].add(a);
        }
        
        // 순위가 매겨진 정점 카운팅
        int rankFixCnt = 0;
        
        // 각 정점을 시작점으로부터 탐색
        // 탐색 시작전 방문 배열 초기화
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n+1];
            int winCnt = dfs(winGraph, i, visited);
            
            visited = new boolean[n+1];
            int loseCnt = dfs(loseGraph, i, visited);
            
            // 자기 자신을 제외한 정점의 총 정점의 개수 - 1과 동일하면 순위 매겨짐
            if (winCnt + loseCnt == n - 1) rankFixCnt++;
        }
        
        return rankFixCnt;
          
    }
    
    private int dfs(List<Integer>[] graph, int start, boolean[] visited) {
        int count = 0;
        visited[start] = true; 
        for (int next : graph[start]) {
            if (!visited[next]) {
                count++; 
                count += dfs(graph, next, visited);
            }
        }
        return count;
    }
}
