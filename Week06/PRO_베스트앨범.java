import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        int n = genres.length;
        for (int i=0; i<n; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<String> sortesGenres = new ArrayList<>(map.keySet());
        
        // 장르별 plays 합산 기준으로 정렬
        sortesGenres.sort((a, b) -> Integer.compare(map.get(b), map.get(a)));
        
        List<Integer> result = new ArrayList<>();
        
        // 정렬된 장르별로 play를 저장 
        List<int[]> playsInGenre = new ArrayList<>();     
        
        // 100
        for (int i=0; i<sortesGenres.size(); i++) {
            // 10^4
            
            for (int j=0; j<n; j++) {
                if (sortesGenres.get(i).equals(genres[j])) {
                    playsInGenre.add(new int[] {plays[j], j});
                }
            }
            
            // 많이 play한 순으로 내림차순 정렬 필요
            playsInGenre.sort((a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            });
            
            for (int j=0; j < playsInGenre.size() && j<2; j++) {
                result.add(playsInGenre.get(j)[1]);  
            }
            
            playsInGenre.clear();
        
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
