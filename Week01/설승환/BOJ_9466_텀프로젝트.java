import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int flag = -1;
    static int[] group;
    static boolean flagB, isCycle;
    static boolean[] visited;
    static Set<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        //학생 수 최대 10만
        //학생 수 몇명인지 주어짐
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());

        for(int i = 0; i < tc; i++){
            n = Integer.parseInt(br.readLine());
            group = new int[n];
            visited = new boolean[n];
            int res = 0;
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                group[j] = Integer.parseInt(st.nextToken()) - 1;
            }
            for(int j = 0; j < n; j++){
                grouping(j);
                res += set.size();
                set.clear();
                flagB = false;
                flag =  - 1;
                isCycle = false;
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    //링크되어서 넘어가면 하나의 그룹을 생성 가능한데, 방문처리 하지 않으면 안됨 안그러면 무한루프돎
    //나 자신을 선택할 수 있고 그러면 나 혼자만 그룹임
    static void grouping(int n){
        //이미 방문한 지역이면 리턴한다. 그런데 cycle이 형성되지 않았다고 해줘야한다.
        if(visited[n]){
            return;
        }
        //사이클 완성
        if(set.contains(n)){
            flag = n;
            isCycle = true;
            set.remove(n);
            return;
        }
        set.add(n);
        grouping(group[n]);
        //팅겨 나올 때 여기서도 set에 값을 제거해주는 과정이 있어야한다.
        if(flag == n){
            flagB = true;
        }
        //그냥 visited로 팅겨저 나오는 경우도 조절하자
        if(!flagB && isCycle){
            set.remove(n);
        }
        visited[n] = true;
    }

}
