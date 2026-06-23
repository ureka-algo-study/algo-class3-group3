import java.util.*;

class Solution {
    
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        Edge[] edges = new Edge[costs.length];
        for(int i = 0; i < costs.length; i++) {
            edges[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
        }
        Arrays.sort(edges);
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for(Edge e : edges) {
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                answer += e.cost;
            }
        }
        
        return answer;
    }

    static int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]); 
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }
}
