// begin에서 targeg으로 변환하는 가장 짧은 변환 과정
// 무가중치이므로 BFS 사용
// target 도달시 순회 깊이를 반환
import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // 문자 하나 다른것을 확인
        // 문자 하나 다른 문자열을 큐에 적재
        // 문자열이 target과 동일하면 순회 깊이 반환
        // return 0 반환
        
        Queue<String> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length]; // words 방문 확인
        q.add(begin);
        int depth = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String cur = q.poll();
                if (target.equals(cur)) return depth;
                for (int i = 0; i < words.length; i++) {
                    if (!visited[i]) {
                        int cnt = 0;
                        for (int j = 0; j < words[i].length(); j++) {
                            if (cur.charAt(j) != words[i].charAt(j)) cnt++;
                        }
                        if (cnt == 1) {
                            visited[i] = true;
                            q.add(words[i]); // 변경된 단어 삽입
                        }
                    }   
                }
                
            }
            depth++;
        }
        return 0;
    }    
}
