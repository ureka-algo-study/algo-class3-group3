import java.util.*;

class Solution {
	public int solution(int[][] board) {
		int answer = 0;
		int n = board.length;
		boolean[][][] visited = new boolean[n][n][4];

		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);

		pq.offer(new int[] {0, 0, 0, 0});
		pq.offer(new int[] {0, 0, 1, 0});

		while(!pq.isEmpty()) {
			int[] temp = pq.poll();
			int y = temp[0];
			int x = temp[1];
			int dir = temp[2];
			int cost = temp[3];

			if(y == n - 1 && x == n - 1) {
				answer = cost;
				break;
			}

			visited[y][x][dir] = true;

			for(int i = 0; i < 4; i++) {
				int nd = (dir + i) % 4;
				int ny = y + dy[nd];
				int nx = x + dx[nd];

				if(ny >= n || nx >= n || ny < 0 || nx < 0) continue;
				if(board[ny][nx] == 1 || visited[ny][nx][nd]) continue;

				if(nd == dir) {
					pq.offer(new int[] {ny, nx, nd, cost + 100});
				} else {
					pq.offer(new int[] {ny, nx, nd, cost + 500 + 100});
				}

			}

		}

		return answer;
	}
}