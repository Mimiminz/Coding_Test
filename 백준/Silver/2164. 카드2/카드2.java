import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int value = Integer.parseInt(br.readLine().trim());
		
		for(int idx = 1; idx <= value; idx++) {
			queue.offer(idx);
		}
		while(queue.size() != 1) {
			queue.poll();
			int num = queue.poll();
			queue.offer(num);
		}
		sb.append(queue.peek());
		System.out.println(sb);
        br.close();
	}
}
