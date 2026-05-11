import java.util.*;

class Solution {

    class Node{
        String word;
        int day;

        Node(String word, int day){
            this.word = word;
            this.day = day;
        }
    }

    boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        return bfs(begin, target, words);
    }

    boolean compare(String s, String compare){
        int n = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != compare.charAt(i)){
                n++;
            }
            if(n > 1){
                return false;
            }
        }
        return n == 1;
    }
    int bfs(String begin, String target, String[] words){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(begin, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            String word = cur.word;
            int day = cur.day;

            if(word.equals(target)){
                return day;
            }
            for(int i = 0; i < words.length; i++){
                //이미 소모한 값이거나 글자 차이가 1개가 아니거나
                if(visited[i] || !compare(word, words[i])){
                    continue;
                }
                visited[i] = true;
                q.add(new Node(words[i], day + 1));
            }
        }
        return 0;
    }

}