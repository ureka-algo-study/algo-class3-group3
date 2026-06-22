import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    boolean[] visited;
    ArrayList<String> allRoutes;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        allRoutes = new ArrayList<>();

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(allRoutes);

        return allRoutes.get(0).split(" ");
    }

    public void dfs(int depth, String curr, String path, String[][] tickets) {
        if (depth == tickets.length) {
            allRoutes.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(curr)) {
              visited[i] = true; // 티켓 사용 처리
  
              dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
  
              visited[i] = false;
            }
        }
    }
}
