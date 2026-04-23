import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][][] cube = new int[5][5][5];
    static int result = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int height = 0; height < 5; height++){
            for(int x = 0; x < 5; x++){
                st = new StringTokenizer(br.readLine());
                for(int y = 0; y < 5; y++){
                    cube[x][y][height] = Integer.parseInt(st.nextToken());
                }
            }
        }
        //stack을 쌓는 순서를 가져오면서 turn을 진행이니까..

        order(0, new int[5], new boolean[5]);

        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }

    }
    //또 판을 랜덤으로 쌓아야함 순열로 쌓아야함
    static void order(int depth, int[]stack, boolean[] isUsed){
        if(depth == 5){
            turn(0, new int[5], stack);
            return;
        }
        for(int i = 0; i < 5; i++){
            if(isUsed[i]){
                continue;
            }
            isUsed[i] = true;
            stack[depth] = i;
            order(depth + 1, stack, isUsed);
            isUsed[i] = false;
        }
    }

    //돌리는 회수 판단. 그 이후 bfs
    static void turn(int depth, int[] arr, int[] stack){
        //5개 다 몇번 돌릴지 채웠으면 그것에 맞춰 각 맵을 돌려줘야함.
        if(depth == 5){
            //돌리고
            int[][][] tmpCube = change(arr);
            //쌓고
            int[][][] rc = new int[5][5][5];
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    for(int k = 0; k < 5; k++){
                        rc[j][k][i] = tmpCube[j][k][stack[i]];
                    }
                }
            }
            //bfs
            bfs(rc);
            return;
        }
        for(int i = 0; i < 4; i++){
            arr[depth] = i;
            turn(depth + 1, arr, stack);
        }
    }
    //큐브 돌리는 작업
    static int[][][] change(int[] arr){
        int[][][] tmp = new int[5][5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    tmp[i][j][k] = cube[i][j][k];
                }
            }
        }
        //i는 층 수임
        for(int i = 0; i < 5; i++){
            switch (arr[i]){
                //0일 때는 시계방향으로 돌지 않는다.
                case 0 :
                    break;
                //1일 때는 시계방향으로 90도만 돈다
                case 1:
                    for(int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            tmp[j][k][i] = cube[k][4 - j][i];
                        }
                    }
                    break;

                // 180도
                case 2:
                    for(int j = 0; j < 5; j++){
                        for(int k = 0; k < 5; k++){
                            tmp[j][k][i] = cube[4 - j][4 - k][i];
                        }
                    }
                    break;
                //3 반시계방향
                default :
                    for(int j = 0; j < 5; j++){
                        for(int k = 0; k < 5; k++){
                            tmp[j][k][i] = cube[4 - k][j][i];
                        }
                    }
            }
        }
        return tmp;
    }

    //돌려진 큐브를 가지고 bfs하는 작업 시작지점이랑 도착지점 중 하나라도 0이면 그냥 result = -1
    //가장 적은 이동회수가 필요했네
    static void bfs(int[][][] cube){
        if(cube[0][0][0] == 0 || cube[4][4][4] == 0){
            return;
        }
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][]visited = new boolean[5][5][5];
        q.add(new int[]{0, 0, 0, 0});//x, y, z, cnt
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cz = cur[2];
            int cnt = cur[3];

            for(int i = 0; i < 6; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nz = cz + dz[i];
                int nc = cnt + 1;

                //범위 넘어가면, 방문했으면, 못가는 위치면
                if(nx < 0 || nx == 5 || ny < 0 || ny == 5 || nz < 0 || nz == 5 || visited[nx][ny][nz] || cube[nx][ny][nz] == 0){
                    continue;
                }
                //다음 위치가 도착 위치면
                if(nx == 4 && ny == 4 && nz == 4){
                    result = Math.min(result, nc);
                    return;
                }

                //그저 갈 수 있는 위치면
                visited[nx][ny][nz] = true;
                q.add(new int[]{nx, ny, nz, nc});
            }
        }
    }
}
