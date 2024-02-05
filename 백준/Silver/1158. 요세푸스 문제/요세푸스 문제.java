import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ 1158 요세푸스 문제
 * @author JOMINJU
 * 
 * 원형 큐 구현
 * 
 * 1. 길이가 N인 visited값을 넣는다.(비트마스킹)
 * 3. k번째 값을 0으로 초기화시킨다.
 * 4. 이후 값이 0이 아니고, (현재 인덱스)%N+k 번째 인덱스를 돌며 0으로 초기화시킨다.
 * 		4-1. 이때 값은 차례대로 sb에 집어넣는다.
 * 5. visited 변수 값이 0이되면 종료한다.
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		int number = Integer.parseInt(st.nextToken());
		int order = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for(int idx = 1; idx <= number; idx++)
			queue.offer(idx);
		
		sb.append("<");
		while(!queue.isEmpty()) {
			for(int idx = 0; idx < order-1; idx++) {
				queue.offer(queue.poll());
			}
			if(queue.size() == 1)
				sb.append(queue.poll()).append(">");
			else
				sb.append(queue.poll()).append(", ");
		}
        System.out.println(sb);
        br.close();	
	}
}