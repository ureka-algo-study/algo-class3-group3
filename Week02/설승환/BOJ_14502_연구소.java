import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int result = Integer.MIN_VALUE;
    static int[][] map;
    static List<int[]> able = new ArrayList<>();
    static List<int[]> forq = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    able.add(new int[]{i, j});
                }
                else if(map[i][j] == 2){
                    forq.add(new int[]{i, j});
                }
            }
        }

        set(0, new ArrayList<>());

        System.out.println(result);
    }
    //세울 벽 위치 정하고 벽을 세우기
    static void set(int start, List<int[]> walls){
        if(walls.size() == 3){
            int[][] tmpMap = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    tmpMap[i][j] = map[i][j];
                }
            }

            for(int[] wall : walls){
                tmpMap[wall[0]][wall[1]] = 1;
            }

            bfs(tmpMap);

            for(int[] wall : walls){
                tmpMap[wall[0]][wall[1]] = 0;
            }
            return;
        }
        for(int i = start; i < able.size(); i++){
            walls.add(able.get(i));
            set(i + 1, walls);
            walls.remove(walls.size() - 1);
        }
    }

    //bfs로 바이러스 퍼뜨리기
    static void bfs(int[][] tmpMap){
        Queue<int[]> q = new ArrayDeque<>(forq);

        while(!q.isEmpty()){
            int[] virus = q.poll();
            int vx = virus[0];
            int vy = virus[1];

            for(int i = 0; i < 4; i++){
                int nx = vx + dx[i];
                int ny = vy + dy[i];

                if(nx >= n|| nx < 0 || ny >= m || ny < 0 || tmpMap[nx][ny] != 0){
                    continue;
                }
                tmpMap[nx][ny] = 2;
                q.add(new int[]{nx, ny});
            }

        }
        count(tmpMap);
    }

    //0 카운트
    static void count(int[][]tmpMap){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(tmpMap[i][j] == 0){
                    cnt++;
                }
            }
        }
        result = Math.max(result, cnt);
    }
}
