import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 16435 스네이크버드
 * @author JOMINJU
 *
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
		int fruitsNum = Integer.parseInt(st.nextToken());
		int snakeBirdLength = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		
		int[] fruits = new int[fruitsNum];
		for(int idx = 0; idx < fruitsNum; idx++)
			fruits[idx] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(fruits);
	
		for(int idx = 0; idx < fruitsNum; idx++) {
			if(fruits[idx] <= snakeBirdLength) {
				snakeBirdLength++;
			}else {
				break;
			}
		}
		sb.append(snakeBirdLength);
		System.out.println(sb);
		br.close();
	}
}