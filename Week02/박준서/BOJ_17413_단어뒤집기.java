import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.LinkedList;  
import java.util.Queue;  
import java.util.Stack;  
  
// <을 만나면 큐에 저장, 큐가 비지 않으면 계속 큐에 저장, >을 만나면 큐에 있던 값 모두 츨략해서 SB에 저장  
// 큐가 비어있으면 모두 스택에 저장, '<'/'공백'/'문자열끝'을 만나면 스택에 있는 값 모두 출력해서 SB에 저장
public class Main {  
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String str = br.readLine();  
        StringBuilder sb = new StringBuilder();  
        Queue<Character> queue = new LinkedList<>();  
        Stack<Character> stack = new Stack<>();  
  
        for (int i = 0; i < str.length(); i++) {  
            char c = str.charAt(i);  
            if (c == '<') {  
                while (!stack.isEmpty()) {  
                    sb.append(stack.pop());  
                }  
                queue.add(c);  
            } else if (c == '>') {  
                queue.add(c);  
                while(!queue.isEmpty()) {  
                    sb.append(queue.remove());  
                }  
            } else if (!queue.isEmpty()) {  
                queue.add(c);  
            } else if (c == ' ') {  
                while (!stack.isEmpty()) {  
                    sb.append(stack.pop());  
                }  
                sb.append(c);  
            } else {  
                stack.push(c);  
            }  
        }  
  
        while (!stack.isEmpty()) {  
            sb.append(stack.pop());  
        }  
  
        System.out.println(sb.toString());  
    }  
}
