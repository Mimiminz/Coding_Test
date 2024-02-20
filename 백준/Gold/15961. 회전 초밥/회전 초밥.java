import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 15961 회전 초밥
 * @author JOMINJU
 *
 * 1. 원형 배열을 선언한다.
 * 2. 한 바퀴를 돌며 주어진 연속 개수만큼 초밥을 먹을 때, 겹치지 않는 최대의 수를 구한다.
 * 3. 이때, 연속되게 먹은 초밥에 쿠폰 번호를 가진 초밥이 없을 경우 경우의 수에 + 1을 한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		int bowlNum = Integer.parseInt(st.nextToken());
		int sushiNum = Integer.parseInt(st.nextToken());
		int continuty = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());
		int[] eaten = new int[sushiNum+1];
		
		int[] sushiList = new int[bowlNum];
		int result = 0;
		int max = 1;
		eaten[coupon] = 1;
		
		for (int line = 0; line < bowlNum; line++) {
			sushiList[line] = Integer.parseInt(br.readLine().trim());
		}
		
		for (int idx = 0; idx < continuty; idx++) {
			if(eaten[sushiList[idx]] == 0){
				max++;
			}
			eaten[sushiList[idx]]++;
		}
		
		int start = 0;
		for (int line = start; line < bowlNum; line++) {
			eaten[sushiList[(line+continuty)%bowlNum]]++;
			if(eaten[sushiList[(line+continuty)%bowlNum]] == 1){
				max++;
			}
			
			eaten[sushiList[line]]--;
			
			if(eaten[sushiList[line]] == 0) {
				max--;
			}
			
			start++;
			result = Math.max(result, max);
			
		}
		
		sb.append(result);
		System.out.println(sb);
		br.close();
			
	}

}