// 문제 정의
// 구하는 답: 당첨 제외될 제제 아이디의 조합 (즉, user_id중에서 banned_id에 포함되는 경우의 수)
// banned_id 하나당 제제 아아디 후보가 여러 명이지만 한 아이디만 골라야한다.
// user_id 8개 이하, banned_id는 8개 이하이므로 완전탐색시 경우의 수는 8!; 시간 복잡도 초과 안남

// 문제 풀이
// banned_id을 기준으로 depth == banned_id.length 만족시 모두 선택 완료해서 하나의 조합 완성
// banned_id[0]의 후보 중 한 명 선택
// banned_id[1]의 후보 중 한 명 선택 (앞에서 선택한 사람 제외)
// 선택된 후보(user_id)는 HashSet에 저장해서 이미저정되어 있다면 후보군 제외해서 중복을 피한다.
// 특정 banned_id와 매칭되는 user_id의 0 인덱스부터 확인해야하므로, 선택한 user_id들의 순서만 다르고 조합 중복 생긴다.
// 따라서 조합의 중복을 제거하기 위해서 조합 완료시 결과값을 HashSet<HashSet<String>>에 저장해서 중복 제거
// 그리고 특정 조합의 중복 제거를 위해 user_id를 저장한 HashSet의 값은 동적으로 변하므로 복사본으로 저장

import java.util.*;

class Solution {
    static int caseCnt = 0; 
    static Set<Set<String>> result = new HashSet<>(); // 동일 원소지만 순서가 다른 중복 조합 제거
    
    public int solution(String[] user_id, String[] banned_id) {    
        backtracking(0,user_id,banned_id, new HashSet<>());
        return result.size();
    }
    
    private void backtracking(int depth, String[] user_id, String[] banned_id, Set<String> selectedUsers) {
        if (depth == banned_id.length) {
            result.add(new HashSet<>(selectedUsers)); // 복사본 저장, 계속 값이 달라짐
            return;
        }
        
        for (int i=0; i<user_id.length; i++) {
            if (selectedUsers.contains(user_id[i])) continue;
            
            if (!isBanUserID(user_id[i], banned_id[depth])) continue;
            
            selectedUsers.add(user_id[i]);
            backtracking(depth+1, user_id, banned_id, selectedUsers);
            selectedUsers.remove(user_id[i]);
        }
    }
    
    private boolean isBanUserID(String user_id, String banned_id) {
        int userLen = user_id.length();
        int banLen = banned_id.length();
        int i=0;
        for (; i<userLen && i<banLen; i++) {
            if (banned_id.charAt(i) != '*' && banned_id.charAt(i) != user_id.charAt(i)) break;    
        }
        if (i == banLen && i == userLen) {
            return true;
        }
        return false;
    }
}
