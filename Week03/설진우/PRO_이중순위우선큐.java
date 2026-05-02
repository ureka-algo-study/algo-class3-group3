import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(String o : operations) {
            if(o.charAt(0) == 'I') {
                String[] strs = o.split(" ");
                int a = Integer.parseInt(strs[1]);
                map.put(a, map.getOrDefault(a, 0) + 1);
            } else if(o.charAt(2) == '-') {
                if(map.isEmpty()) continue;
                int min = map.firstKey();
                if(map.get(min) > 1) {
                    map.put(min, map.get(min) - 1);
                } else {
                    map.remove(min);
                }
            } else {
                if(map.isEmpty()) continue;
                int max = map.lastKey();
                if(map.get(max) > 1) {
                    map.put(max, map.get(max) - 1);
                } else {
                    map.remove(max);
                }
            }
        }
        
        if(map.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = map.lastKey();
            answer[1] = map.firstKey();
        }

        return answer;
    }
}
