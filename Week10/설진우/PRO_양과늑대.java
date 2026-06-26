import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    ArrayList<Integer>[] childs;
    int[] nodeInfo;
    int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        nodeInfo = info;
        childs = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            childs[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            childs[edge[0]].add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);

        dfs(0, 0, 0, nextNodes);

        return maxSheep;
    }

    private void dfs(int currentNode, int sheep, int wolf, List<Integer> nextNodes) {
        if (nodeInfo[currentNode] == 0)
            sheep++;
        else
            wolf++;

        if (wolf >= sheep)
            return;

        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> newNextNodes = new ArrayList<>(nextNodes);

        newNextNodes.remove(Integer.valueOf(currentNode));

        for (int child : childs[currentNode]) {
            newNextNodes.add(child);
        }

        for (int nextNode : newNextNodes) {
            dfs(nextNode, sheep, wolf, newNextNodes);
        }
    }
}
