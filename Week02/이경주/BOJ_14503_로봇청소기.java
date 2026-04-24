import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0;

		int[] dy = {-1, 0, 1, 0};
		int[] dx = {0, 1, 0, -1};

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});

		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int y = temp[0];
			int x = temp[1];

			if(arr[y][x] == 0) {
				arr[y][x] = 2;
				count++;
			}

			boolean check = false;
			for(int i = 0; i < 4; i++) {

				int ny = y + dy[i];
				int nx = x + dx[i];

				if(ny >= n || nx >= m || ny < 0 || nx < 0) continue;

				if(arr[ny][nx] == 0) {
					check = true;
					break;
				}
			}

			if(!check) {
				int ny = y - dy[dir];
				int nx = x - dx[dir];

				if(ny >= n || nx >= m || ny < 0 || nx < 0 || arr[ny][nx] == 1) break;

				q.offer(new int[] {ny, nx});
			} else {
				dir = (dir + 3) % 4;

				int ny = y + dy[dir];
				int nx = x + dx[dir];

				if(arr[ny][nx] == 0) {
					q.offer(new int[] {ny, nx});
				} else {
					q.offer(new int[] {y, x});
				}
			}

		}

		System.out.println(count);
	}
}