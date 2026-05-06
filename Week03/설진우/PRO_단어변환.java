import java.util.*;

class Solution {
  
    class Node {
        String word;
        int steps;

        Node(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
  
    public int solution(String begin, String target, String[] words) {
        boolean containsTarget = false;
        for (String word : words) {
            if (word.equals(target)) {
                containsTarget = true;
                break;
            }
        }
        if (!containsTarget) return 0;

        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        queue.offer(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.word.equals(target)) {
                return current.steps;
            }

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canConvert(current.word, words[i])) {
                    visited[i] = true;
                    queue.offer(new Node(words[i], current.steps + 1));
                }
            }
        }

        return 0; 
    }

    private boolean canConvert(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) return false;
        }
        return diffCount == 1;
    }
}
