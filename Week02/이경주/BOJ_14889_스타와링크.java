import java.io.*;
import java.util.*;

class Main {

	static int n;
	static int min = Integer.MAX_VALUE;

	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());

		visited = new boolean[n];
		arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);

		System.out.println(min);
	}

	private static void dfs(int depth, int index) {

		if(depth == n / 2) {
			int sumStar = 0;
			int sumLink = 0;

			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					if(visited[i] && visited[j]) sumStar += arr[i][j] + arr[j][i];
					if(!visited[i] && !visited[j]) sumLink += arr[i][j] + arr[j][i];
				}
			}

			min = Math.min(min, Math.abs(sumStar - sumLink));
			return;
		}

		for(int i = index; i < n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(depth + 1, i);
			visited[i] = false;
		}

	}

}