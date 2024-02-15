import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int customerNum;
	public static int[][] customers;
	public static boolean[] visited;
	public static int[] company = new int[2];
	public static int[] home = new int[2];
	public static int distance;

	
	public static void dfs(int startX, int startY, int near, int num) {
		if(num == customerNum) {
			near += Math.abs(startX - home[0]) + Math.abs(startY - home[1]);
			distance = Math.min(distance, near);
			return;
		}
		for(int idx = 0; idx < customerNum; idx++) {
			if(!visited[idx]) {
				int curDistance = Math.abs(startX - customers[idx][0]) + Math.abs(startY - customers[idx][1]);
				visited[idx] = true;
				dfs(customers[idx][0], customers[idx][1], near+curDistance, num+1);
				visited[idx] = false;
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			customerNum = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine().trim());
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			customers = new int[customerNum][2];
			visited = new boolean[customerNum];
			distance = Integer.MAX_VALUE;
			for(int num = 0; num < customerNum; num++) {
				customers[num][0] = Integer.parseInt(st.nextToken());
				customers[num][1] = Integer.parseInt(st.nextToken());
			}
			dfs(company[0], company[1], 0,0);
			sb.append("#").append(tc).append(" ").append(distance).append("\n");
		}
		System.out.println(sb);
	}
}
