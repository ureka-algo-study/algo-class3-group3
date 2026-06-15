package programmers;

import java.util.HashSet;
import java.util.Set;

public class BadUser {
    Set<Set<String>> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }

        dfs(new HashSet<>(), user_id, banned_id, 0);

        return resultSet.size();
    }

    private void dfs(Set<String> currentSet, String[] user_id, String[] banned_id, int bIdx) {
        if (bIdx == banned_id.length) {
            resultSet.add(new HashSet<>(currentSet));
            return;
        }

        String regex = banned_id[bIdx];

        for (String user : user_id) {
            if (!currentSet.contains(user) && user.matches(regex)) {
                currentSet.add(user);

                dfs(currentSet, user_id, banned_id, bIdx + 1);

                currentSet.remove(user);
            }
        }
    }
}
