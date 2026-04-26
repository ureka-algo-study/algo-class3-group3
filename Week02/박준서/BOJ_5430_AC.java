import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.*;  
  
// 뒤집기기는 배열을 순회해야하므로 10^5 걸린다.  
// 맨앞 인덱스 요소 삭제는 배열을 순회해야하므로 10^5 걸린다.  
// 최대 뒤집기 회수: 10^5  
// 배열 순회 x 연산 = 10^5 x 10^5 = 10^10 -> 미통과  
// 뒤집기와 맨 앞 인덱스 요소 삭제 시간 복잡도를 줄여야한다.  
// 뒤집기는 직접 수행하기 보다 뒤집기 한척을 하고 출력할때만 반대로 출력할수있다. 그러면 O(1)으로 뒤집기를 해결가능하다.  
// 허나 뒤집기의 유무에 따라 맨앞요소 삭제시 로직에 문제가 발생한다.  
// Dequeue를 사용하면 뒤집기 유무에 따라 맨앞 혹은 맨뒤의 요소 제거가 O(1)으로 가능하다.  
public class Main {  
  
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        int t = Integer.parseInt(br.readLine());  
        while (t > 0) {  
            String func = br.readLine();  
            int n = Integer.parseInt(br.readLine());  
            String array = br.readLine();  
            String arr = array.substring(1, array.length() - 1);  
            String[] tokens = arr.split(",");  
            boolean reverse = false;  
            boolean error = false;  
            Deque<String> deque = new ArrayDeque<>();  
            for (int i = 0; i < n; i++) {  
                deque.addLast(tokens[i]);  
            }  
            for (char c: func.toCharArray()) {  
                if (c == 'R') {  
                    reverse = !reverse;  
                } else if (c == 'D') {  
                    if (deque.isEmpty()) {  
                        error = true;  
                        break;  
                    }  
                    if (reverse) {  
                        deque.pollLast();  
                    } else {  
                        deque.pollFirst();  
                    }  
                }  
            }  
            if (error) {  
                System.out.println("error");  
            } else {  
                StringBuilder sb = new StringBuilder();  
                sb.append("[");  
                while (deque.size() > 1) {  
                    sb.append(reverse ? deque.pollLast() : deque.pollFirst());  
                    sb.append(",");  
                }  
                if (!deque.isEmpty()) {  
                    sb.append(reverse ? deque.pollLast() : deque.pollFirst());  
                }  
                sb.append("]");  
                System.out.println(sb.toString());  
            }  
            t--;  
        }  
    }  
}
