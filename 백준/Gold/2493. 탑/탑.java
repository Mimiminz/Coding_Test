import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ 2493 탑
 * @author JOMINJU
 * 
 * 1. 
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int number = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		Stack<int[]> stack = new Stack<int[]>();
		int[] towel = new int[number];
		for(int idx = 0; idx < number; idx++)
			towel[idx] = Integer.parseInt(st.nextToken());
		
		for(int idx = 0; idx < number; idx++) {
			while(!stack.isEmpty()) {
				if(stack.peek()[1] > towel[idx]) {
					sb.append(stack.peek()[0]+1).append(" ");
					break;
				}else
					stack.pop();
			}
			if(stack.isEmpty())
				sb.append(0).append(" ");
			int[] temp = {idx, towel[idx]};
			stack.push(temp);
		}
		
		System.out.println(sb);
		br.close();
	}
}