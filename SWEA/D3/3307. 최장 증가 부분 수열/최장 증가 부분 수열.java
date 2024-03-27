import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 3307 최장 증가 부분 수열
 * @author JOMINJU
 *
 * dp를 이용한 치장 증가 부분 수열 풀기
 * 
 *  1. 
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int arrLength;
	public static int[] arr;
	public static int[] maxLength;
	
	public static int answer;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			arrLength = Integer.parseInt(br.readLine().trim());
			arr = new int[arrLength];
			maxLength = new int[arrLength];
			
			st = new StringTokenizer(br.readLine().trim());
			
			for (int idx = 0; idx < arrLength; idx++) {
				arr[idx] = Integer.parseInt(st.nextToken());
			}
			
			for (int idx = 0; idx < arrLength; idx++) {
				maxLength[idx] = 1;
				for (int value = 0; value < idx; value++) {
					if(arr[value] < arr[idx] && maxLength[idx] < maxLength[value] + 1) {
						maxLength[idx] = maxLength[value] + 1;
					}
				}
			}
			
			answer = 0;
			
			for (int idx = 0; idx < arrLength; idx++) {
				if(answer < maxLength[idx]) {
					answer = maxLength[idx];
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
