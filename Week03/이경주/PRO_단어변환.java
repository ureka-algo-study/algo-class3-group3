class Solution {

	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public int solution(String begin, String target, String[] words) {

		visited = new boolean[words.length];
		dfs(begin, target, words, 0);
		if(Integer.MAX_VALUE == min) return 0;
		return min;
	}

	private static void dfs(String begin, String target, String[] words, int count) {

		if(begin.equals(target)) {
			min = Math.min(min, count);
			return;
		}

		for(int i = 0; i < words.length; i++) {
			if(visited[i]) continue;

			int check = 0;
			for(int j = 0; j < begin.length(); j++) {
				if(begin.charAt(j) == words[i].charAt(j)) check++;
			}

			if(check == begin.length() - 1) {
				visited[i] = true;
				dfs(words[i], target, words, count + 1);
				visited[i] = false;
			}

		}

	}

}