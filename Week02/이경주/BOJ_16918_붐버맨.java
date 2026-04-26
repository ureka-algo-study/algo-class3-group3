import java.io.*;
import java.util.*;

class Main {

	static int r;
	static int c;
	static int n;

	static char[][] arr;
	static Queue<int[]> q = new LinkedList<>();

	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		arr = new char[r][c];
		for(int i = 0; i < r; i++) {
			String s = br.readLine();
			for(int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		for(int i = 2; i <= n; i++) {
			if(i % 2 == 0) {
				fill();
			} else {
				bomb();
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void fill() {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(arr[i][j] == 'O') q.add(new int[] {i, j});
				arr[i][j] = 'O';
			}
		}
	}

	private static void bomb() {
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int y = temp[0];
			int x = temp[1];

			arr[y][x] = '.';

			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if(ny >= r || nx >= c || ny < 0 || nx < 0) continue;

				arr[ny][nx] = '.';
			}
		}
	}

}