import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    class Track implements Comparable<Track> {
        int r, c, dir, cost;

        Track(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Track o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int[][][] dist = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Track> pq = new PriorityQueue<>();
        pq.offer(new Track(0, 0, -1, 0));
        
        for (int i = 0; i < 4; i++) {
            dist[0][0][i] = 0;
        }

        while (!pq.isEmpty()) {
            Track curr = pq.poll();

            if (curr.r == n - 1 && curr.c == n - 1) {
                return curr.cost;
            }

            if (curr.dir != -1 && dist[curr.r][curr.c][curr.dir] < curr.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n && board[nr][nc] == 0) {
                    int nextCost = curr.cost;

                    if (curr.dir == -1 || curr.dir == i) {
                        nextCost += 100;
                    } else {
                        nextCost += 600;
                    }

                    if (nextCost < dist[nr][nc][i]) {
                        dist[nr][nc][i] = nextCost;
                        pq.offer(new Track(nr, nc, i, nextCost));
                    }
                }
            }
        }
        return 0;
    }
}
