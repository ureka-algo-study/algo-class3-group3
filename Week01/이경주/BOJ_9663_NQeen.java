import java.io.*;

class BOJ_9663_NQeen {

	static int n;
	static int count;

	static boolean[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		count = 0;

		board = new boolean[n][n];

		dfs(0);

		System.out.println(count);
	}

	private static void dfs(int depth) {
		if(depth == n) {
			count++;
			return;
		}

		for(int i = 0; i < n; i++) {
			if(check(depth, i)) {
				board[depth][i] = true;
				dfs(depth + 1);
				board[depth][i] = false;
			}
		}

	}

	private static boolean check(int y, int x) {
		for(int i = 0; i < y; i++) {
			if (board[i][x]) return false;
		}

		int r = y - 1;
		int c = x - 1;
		while(r >= 0 && c >= 0) {
			if (board[r][c]) return false;
			r--;
			c--;
		}

		r = y - 1;
		c = x + 1;
		while(r >= 0 && c < n) {
			if (board[r][c]) return false;
			r--;
			c++;
		}

		return true;
	}

}