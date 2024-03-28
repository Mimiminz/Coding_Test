
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 9205 맥주 마시면서 걸어가기
 * @author JOMINJU
 *
 * 참고사항
 *	1. 처음 이동 시 맥주 20개, 즉 1000미터를 이동할 수 있다.
 *	2. 편의점에 들릴 시 그냥 20개로 리셋
 *
 *
 *  1. 상근이의 현재 위치를 큐에 넣으면서 축제에 도착하거나 큐가 비면 종료한다.
 *  2. 방문한 편의점은 방문 표시를 한다.
 *  3. 편의점이 현재 상근이의 위치보다 축제에 가까우면 큐에 넣는다.
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int shopNum;
	public static int[] home;
	public static int[][] shop;
	public static boolean[] visited;
	public static int[] festival;
	
	public static boolean moveToFestival() {
		Queue<int[]> queue = new LinkedList<>();
		// 현재 row, 현재 col
		queue.add(new int[] {home[0], home[1]});
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int row = current[0];
			int col = current[1];
			int distance = Math.abs(festival[0] - row) + Math.abs(festival[1] - col);
			
			if(distance <= 1000) {
				return true;
			}
			
			for (int idx = 0; idx < shopNum; idx++) {
				if(visited[idx]) {
					continue;
				}
				
				int currentToShop = Math.abs(shop[idx][0] - row) + Math.abs(shop[idx][1] - col);
				int shopToFestival = Math.abs(festival[0] - shop[idx][0]) 
						+ Math.abs(festival[1] - shop[idx][1]);
				
				if(currentToShop <= 1000) {
					visited[idx] = true;
					queue.add(new int[] {shop[idx][0], shop[idx][1]});
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			shopNum = Integer.parseInt(br.readLine().trim());
			shop = new int[shopNum][2];
			visited = new boolean[shopNum];
		
			st = new StringTokenizer(br.readLine().trim());
			home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			for (int idx = 0; idx < shopNum; idx++) {
				st = new StringTokenizer(br.readLine().trim());

				shop[idx][0] = Integer.parseInt(st.nextToken());
				shop[idx][1] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine().trim());
			festival = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			if(moveToFestival()) {
				sb.append("happy\n");
			}else {
				sb.append("sad\n");
			}
		}
		
		System.out.println(sb);
		br.close();
	}
}
