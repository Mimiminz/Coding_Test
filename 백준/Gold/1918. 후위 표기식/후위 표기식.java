import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String notation = br.readLine().trim();
        StringBuilder sb = new StringBuilder();

        Stack<Character> operationStack = new Stack<>();

        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);

        for (char current : notation.toCharArray()) {
            if (Character.isLetter(current)) {
                sb.append(current); // 피연산자는 바로 결과에 추가
            } else if (current == '(') {
                operationStack.push(current); // 여는 괄호는 스택에 추가
            } else if (current == ')') {
                // 닫는 괄호는 여는 괄호가 나올 때까지 연산자를 pop
                while (!operationStack.isEmpty() && operationStack.peek() != '(') {
                    sb.append(operationStack.pop());
                }
                operationStack.pop(); // 여는 괄호 제거
            } else { 
                // 연산자 처리
                while (!operationStack.isEmpty() && operationStack.peek() != '(' &&
                        precedence.get(current) <= precedence.get(operationStack.peek())) {
                    sb.append(operationStack.pop());
                }
                operationStack.push(current);
            }
        }

        // 남아 있는 연산자 처리
        while (!operationStack.isEmpty()) {
            sb.append(operationStack.pop());
        }

        System.out.println(sb);
    }
}
