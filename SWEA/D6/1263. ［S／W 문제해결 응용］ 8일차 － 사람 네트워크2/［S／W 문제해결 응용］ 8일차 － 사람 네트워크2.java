import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 1263 사람 네트워크2
 * @author JOMINJU
 *
 * 1. 
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static final int INF = 10000;
	
	public static int number;
	public static int[][] connection;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			number = Integer.parseInt(st.nextToken());
			connection = new int[number][number];
			
			for (int row = 0; row < number; row++) {
				for (int col = 0; col < number; col++) {
					connection[row][col] = Integer.parseInt(st.nextToken());
					// 시작 사람과 도착 사람이 다르고, 연결되어있지 않을때 최대값으로 초기화
					if(connection[row][col] == 0 && row != col) {
						connection[row][col] = INF;
					}
				}
			}
			
			for (int idx = 0; idx < number; idx++) {
				for (int start = 0; start < number; start++) {
					
					// 경유하는 사람과 시작 사람이 같을 경우 패스
					if(idx == start) {
						continue;
					}
					
					for (int goal = 0; goal < number; goal++) {
						connection[start][goal] = Math.min(connection[start][idx] + connection[idx][goal], 
								connection[start][goal]);
					}
				}
			}
			
			int minResult = INF;
			for (int idx = 0; idx < number; idx++) {
				int sum = 0;
				for (int num = 0; num < number; num++) {
					if(num != idx) {
						sum += connection[idx][num];
					}
				}
				minResult = Math.min(minResult, sum);
				
			}
			sb.append("#").append(tc).append(" ").append(minResult).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
