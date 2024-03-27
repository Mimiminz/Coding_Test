import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int number;
	public static int limitWeight;
	public static int[][] things;
	public static int[][] values;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		number = Integer.parseInt(st.nextToken());
		limitWeight = Integer.parseInt(st.nextToken());
		things = new int[number+1][2];
		values = new int[number+1][limitWeight+1];
		
		for (int idx = 1; idx <= number; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			things[idx][0] = Integer.parseInt(st.nextToken());
			things[idx][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int idx = 1; idx <= number; idx++) {
			for (int weight = 1; weight <= limitWeight; weight++) {
				// 물건의 무게가 배낭의 무게를 넘어갈 때
				if(things[idx][0] > weight) {
					values[idx][weight] = values[idx-1][weight];					
				} // 물건의 무게가 넘지 않을 때
				else {
					values[idx][weight] = Math.max(values[idx-1][weight-things[idx][0]] + things[idx][1], values[idx-1][weight]);
				}
			}
		}
		
		sb.append(values[number][limitWeight]);
		System.out.println(sb);
		br.close();
	}
}