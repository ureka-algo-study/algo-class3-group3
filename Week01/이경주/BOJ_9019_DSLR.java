import java.io.*;
import java.util.*;

class Main {

	static class Node {
		int num;
		String cmd;

		public Node(int num, String cmd) {
			this.num = num;
			this.cmd = cmd;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int ts= 0; ts < t; ts++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// Queue<int[]> q = new LinkedList<>(); // 명령어 기록할게 필요 
			Queue<Node> q = new LinkedList<>();
			boolean[] visited = new boolean[10001]; //시간 초과나서 추가
			q.offer(new Node(a, ""));
			visited[a + 1] = true;
			
			while(!q.isEmpty()) {
				Node node = q.poll();
				
				if(node.num == b) {
					sb.append(node.cmd).append("\n");
					break;
				}

				for(int i = 0; i < 4; i++) {
					if(i == 0) {
						int result = D(node.num);
						if(!visited[result + 1]) {
							visited[result] = true;
							q.offer(new Node(result, node.cmd + "D"));
						}
					} else if(i == 1) {
						int result = S(node.num);
						if(!visited[result + 1]) {
							visited[result] = true;
							q.offer(new Node(result, node.cmd + "S"));
						}
					} else if(i == 2) {
						int result = L(node.num);
						if(!visited[result + 1]) {
							visited[result] = true;
							q.offer(new Node(result, node.cmd + "L"));
						}
					} else {
						int result = R(node.num);
						if(!visited[result + 1]) {
							visited[result] = true;
							q.offer(new Node(result, node.cmd + "R"));
						}
					}
				}
				
			}
			
		}
		
		System.out.println(sb);
	}

	private static int D(int a) {
		if(a * 2 > 9999) {
			return (a * 2) % 10000;
		}
		return a * 2;
	}

	private static int S(int a) {
		if(a == 0) {
			return 9999;
		}
		return a - 1;
	}

	private static int L(int a) {
		// String temp = String.valueOf(a);
		// temp += temp.charAt(0);
		// temp = temp.substring(1);
		
		// return Integer.parseInt(temp);
		// 이부분 AI 도움
		return (a % 1000) * 10 + (a / 1000);
	}

	private static int R(int a) {
		// String temp = String.valueOf(a);
		// temp = temp.charAt(temp.length() - 1) + temp;
		// temp = temp.substring(1, temp.length());
		
		// return Integer.parseInt(temp);
		// 이부분 AI 도움
		return (a % 10) * 1000 + (a / 10);
	}
	
}