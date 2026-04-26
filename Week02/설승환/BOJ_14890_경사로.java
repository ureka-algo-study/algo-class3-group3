import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, l, result = 0;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            if(check(map[i])){
                result++;
            }
            int[] tmp = new int[n];
            for(int j = 0; j < n; j++){
                tmp[j] = map[j][i];
            }
            if(check(tmp)){
                result++;
            }
        }

        System.out.println(result);
    }
    static boolean check(int[] nums){
        boolean[] used = new boolean[n];

        for(int i = 0; i < n - 1; i++){
            //높이가 같은 경우
            if(nums[i] == nums[i + 1]){
                continue;
            }
            //2 이상 높이 차이 나는 경우
            if(Math.abs(nums[i] - nums[i + 1]) > 1){
                return false;
            }
            //오르막인 경우
            if(nums[i] < nums[i + 1]){
                //현재 칸 포함 뒤로 L칸이 같은 높이여야함
                for(int j = 0; j < l; j++){
                    if(i - j < 0 || nums[i] != nums[i - j] || used[i - j]){
                        return false;
                    }
                    used[i - j] = true;
                }
            }
            //내리막인 경우
            else{
                //다음칸포함 L칸이 같은 높이여야함
                for(int j = 1; j <= l; j++){
                    //범위를 넘기거나, 값이 다르거나, 이미 경사로가 설치되었거나
                    if(i + j >= n || nums[i + 1] != nums[i + j] || used[i + j]){
                        return false;
                    }
                    used[i + j] = true;
                }
            }
        }
        return true;
    }

}
