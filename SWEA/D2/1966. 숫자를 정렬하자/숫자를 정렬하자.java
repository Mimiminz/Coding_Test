import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[] arrays;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			int number = Integer.parseInt(br.readLine().trim());
			arrays = new int[number];
			st = new StringTokenizer(br.readLine().trim());
			for(int num = 0; num < number; num++) {
				arrays[num] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arrays);
			sb.append("#").append(tc);
			for(int num = 0; num < number; num++) {
				sb.append(" ").append(arrays[num]);
			}
			sb.append("\n");
		}	
		System.out.println(sb);
	}
}
