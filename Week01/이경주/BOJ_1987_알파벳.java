import java.io.*;
import java.util.*;

class Main {

	static int r;
	static int c;
	static int max;

	static char[][] arr;

	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};

	static Set<Character> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[r][c];
		for(int i = 0; i < r; i++) {
			String s = br.readLine();
			for(int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		max = 1;
		dfs(0, 0);
		set.add(arr[0][0]);

		System.out.println(max);
	}

	private static void dfs(int y, int x) {
		int check = 0;
		set.add(arr[y][x]);

		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if(ny >= r || nx >= c || ny < 0 || nx < 0) {
				check++;
				continue;
			}

			if(set.contains(arr[ny][nx])) {
				check++;
				continue;
			}

			dfs(ny, nx);
		}

		if(check == 4) {
			max = Math.max(max, set.size());
		}

		set.remove(arr[y][x]);
	}

}