import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/** 
 * BOJ 2164 카드 2
 * @author JOMINJU
 * 
 * 1. queue에 1부터 N까지 값을 집어넣는다.
 * 2. 큐의 제일 왼쪽 값은 버린다.
 * 3. 그 다음 왼쪽 값은 오른쪽에 넣는다.
 * 4. 큐의 길이가 1이 될때까지 반복한다.
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int value = Integer.parseInt(br.readLine().trim());
		// 1. queue에 1부터 N까지 값을 집어넣는다.
		for(int idx = 1; idx <= value; idx++) {
			queue.offer(idx);
		}
		// 4. 큐의 길이가 1이 될때까지 반복한다.
		while(queue.size() != 1) {
			// 2. 큐의 제일 왼쪽 값은 버린다.
			queue.poll();
			// 3. 그 다음 왼쪽 값은 오른쪽에 넣는다.
			queue.offer(queue.poll());
		}
		sb.append(queue.peek());
		System.out.println(sb);
		br.close();
	}
}
