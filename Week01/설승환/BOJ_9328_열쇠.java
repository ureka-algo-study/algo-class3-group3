import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int h, w;
    static char[][] map;
    static Set<Character> keys;
    static Map<Character, List<int[]>> doors;
    static Queue<int[]> q;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            keys = new HashSet<>();
            q = new ArrayDeque<>();
            visited = new boolean[h + 2][w + 2];
            doors = new HashMap<>();
            //맵을 일단 길로 채움
            for(int j = 0; j < h + 2; j++){
                Arrays.fill(map[j], '.');
            }
            //map에 값을 채움 이 때 문이면 doors에 저장
            for(int j = 1; j <= h; j++){
                String s = br.readLine();
                for(int k = 1; k <= w; k++){
                    map[j][k] = s.charAt(k - 1);
                }
            }
            //key 저장
            String s = br.readLine();
            for(int j = 0; j < s.length(); j++){
                keys.add(s.charAt(j));
            }

            q.add(new int[]{0, 0});
            visited[0][0] = true;

            bfs();
        }
        System.out.println(sb);
    }

    static void bfs(){
        int result = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for(int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 넘어가거나 벽이거나 방문한 경우 넘어간다
                if(nx >= h + 2 || nx < 0 || ny < 0 || ny >= w + 2 || visited[nx][ny] || map[nx][ny] == '*'){
                    continue;
                }
                // 문을 발견한 경우
                if(Character.isUpperCase(map[nx][ny])){
                    // 맞는 키가 있는 경우 방문처리하고 q에 추가
                    if(keys.contains(Character.toLowerCase(map[nx][ny]))){
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                    // 키가 없으면 맵에 새로 추가
                    else{
                        doors.computeIfAbsent(map[nx][ny], item -> new ArrayList<>()).add(new int[]{nx, ny});
                    }
                }
                // 키를 발견한 경우 키를 추가하고 방문처리하는데, 문을 다시 살펴보고 열지 못했던 키에 맞는 문이 있으면 열고 q에 추가
                else if(Character.isLowerCase(map[nx][ny])){
                    keys.add(map[nx][ny]);
                    if(doors.containsKey(Character.toUpperCase(map[nx][ny]))){
                        List<int[]> matchDoors = doors.remove(Character.toUpperCase(map[nx][ny]));
                        if(matchDoors != null){
                            for(int[] door : matchDoors){
                                visited[door[0]][door[1]] = true;
                                q.add(new int[]{door[0], door[1]});
                            }
                        }
                    }
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
                // 먹을 놈이면 res++하고 방문처리
                else if(map[nx][ny] == '$'){
                    result++;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
                else{
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        sb.append(result).append('\n');
    }
}
