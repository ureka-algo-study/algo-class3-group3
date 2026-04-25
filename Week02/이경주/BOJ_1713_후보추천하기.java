import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int total = Integer.parseInt(br.readLine());

		Map<Integer, int[]> map = new HashMap<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < total; i++) {
			int student = Integer.parseInt(st.nextToken());

			if(map.containsKey(student)) {
				map.get(student)[0]++;
				continue;
			}

			if(map.size() == n) {
				int minKey = Integer.MAX_VALUE;
				int minCnt = Integer.MAX_VALUE;
				int oldTime = Integer.MAX_VALUE;

				for(Integer key : map.keySet()) {
					int[] info = map.get(key);
					int cnt = info[0];
					int time = info[1];

					if(cnt < minCnt) {
						minKey = key;
						minCnt = cnt;
						oldTime = time;
					} else if(cnt == minCnt && time < oldTime) {
						oldTime = time;
						minKey = key;
					}
				}

				map.remove(minKey);
			}

			map.put(student, new int[]{1, i});
		}

		List<Integer> result = new ArrayList<>(map.keySet());
		Collections.sort(result);

		for(int i : result) {
			System.out.print(i + " ");
		}

	}
}