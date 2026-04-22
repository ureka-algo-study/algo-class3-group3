package bj.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ11559 {
    static boolean[][] visited;
    static boolean notBlown = false;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        int time = 0;
        for(int i = 0; i < 12; i++){
            String s = br.readLine();
            for(int j = 0; j < 6; j++){
                map[i][j] = s.charAt(j);
            }
        }
        //터지는 일이 발생하지 않을 때 까지
        while(!notBlown){
            visited = new boolean[12][6];
            notBlown = true;
            //맵을 돌면서
            for(int i = 11; i >= 0; i--){
                for(int j = 0; j < 6; j++){
                    //.이 아니라면 그 값을 주변에서 돌면서 찾고 4개 이상이면 터진다.
                    if(map[i][j] != '.' && !visited[i][j]){
                        visited[i][j] = true;
                        puyo(i, j);
                    }
                }
            }
            //터지지 않았다면
            if(notBlown){
                System.out.println(time);
                return;
            }
            //터졌다면
            time++;
            letDown();
        }
    }
    //while문으로 끝날 때 까지 돌면 while의 조건은 그 턴에 터졌냐 안터졌냐 터지면 notBlown = false;
    static void puyo(int x, int y){
        //bfs로 돌면서 4개 이상 되면 전부 . 으로 만들어버림
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> ls = new ArrayList<>();
        q.add(new int[]{x, y});
        ls.add(new int[]{x, y});
        char ch = map[x][y];
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            //사방 돌면서 ch와 같은 것만 넣으면서 cnt++
            for(int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx >= 12 || ny >= 6 || nx < 0 || ny < 0 || map[nx][ny] != ch || visited[nx][ny]){
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                ls.add(new int[]{nx, ny});
            }

        }
        if(ls.size() >= 4){
            for(int[] spot : ls){
                map[spot[0]][spot[1]] = '.';
            }
            notBlown = false;
        }
    }
    //만약 터졌으면 아래로 끌어 당기는 함수 필요
    static void letDown(){
        for(int i = 0; i < 6; i++){
            int spot = 11;
            Queue<Character> q = new ArrayDeque<>();
            for(int j = 11; j >= 0; j--){
                //알파벳이면 일단 q에 넣고
                if(map[j][i] != '.'){
                    q.add(map[j][i]);
                    map[j][i] = '.';
                }
            }
            while(!q.isEmpty()){
                char alp = q.poll();
                map[spot][i] = alp;
                spot--;
            }
        }
    }
}
