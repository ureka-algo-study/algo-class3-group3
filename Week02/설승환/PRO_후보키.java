import java.util.*;

class Solution {
    List<List<Integer>> candidateKeys = new ArrayList<>();
    String[][] relation;
    public int solution(String[][] rel) {
        relation = rel;
        for (int i = 1; i <= relation[0].length; i++) {
            candidates(0, i, new ArrayList<>());
        }
        return candidateKeys.size();
    }
    //후보키 조합 선정 후 중복 확인 후보키는 그냥 idx로 따짐
    void candidates(int start, int size, List<Integer> list){
        if (list.size() == size) {
            if (isMinimum(list) && find(list)) {
                candidateKeys.add(new ArrayList<>(list));
            }
            return;
        }
        //유일하지 않은 경우
        for(int i = start; i < relation[0].length; i++){
            list.add(i);
            candidates(i + 1, size, list);
            list.remove(list.size() - 1);
        }

    }

    boolean isMinimum(List<Integer> list){
        for(List<Integer> candidate: candidateKeys){
            if(list.containsAll(candidate)){
                return false;
            }
        }
        return true;
    }

    //find함수는 string[][] relation에서 이제 list에 있는 idx 번호 요소들 가지고 중복 판단.
    //set을 하나 두고 여기에 넣으면서 contains로 판단.
    boolean find(List<Integer> list){
        if(list.isEmpty()){
            return false;
        }

        Set<String> set = new HashSet<>();

        for (String[] strings : relation) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : list) {
                sb.append(strings[integer])
                        .append("/");
            }
            //중복 요소라면
            if (!set.add(sb.toString())) {
                return false;
            }
        }
        return true;
    }
}