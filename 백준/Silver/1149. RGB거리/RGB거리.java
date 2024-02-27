import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1149 RGB거리
 * @author JOMINJU
 *
 * 자신의 앞 뒤에 있는 집과는 색이 같으면 안된다.
 * 
 * 1. 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int homeCount;
	public static int[][] homeColor;
	public static int[][] minMoney;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		homeCount = Integer.parseInt(br.readLine().trim());
		homeColor = new int[homeCount][3];
		minMoney = new int[homeCount][3];
		
		for (int idx = 0; idx < homeCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());	
			homeColor[idx][0] = Integer.parseInt(st.nextToken());
			homeColor[idx][1] = Integer.parseInt(st.nextToken());
			homeColor[idx][2] = Integer.parseInt(st.nextToken());
		}
		
		minMoney[0] = new int[] {homeColor[0][0], homeColor[0][1], homeColor[0][2]};
		
		for (int idx = 1; idx < homeCount; idx++) {
			minMoney[idx][0] = Math.min(minMoney[idx-1][1], minMoney[idx-1][2]) + homeColor[idx][0];
			minMoney[idx][1] = Math.min(minMoney[idx-1][0], minMoney[idx-1][2]) + homeColor[idx][1];
			minMoney[idx][2] = Math.min(minMoney[idx-1][0], minMoney[idx-1][1]) + homeColor[idx][2];
		}
		
		sb.append(Math.min(minMoney[homeCount-1][0], Math.min(minMoney[homeCount-1][1], minMoney[homeCount-1][2])));
		System.out.println(sb);
		br.close();
	}
}