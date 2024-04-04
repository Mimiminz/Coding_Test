import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * SWEA 1249 보급로
 * @author JOMINJU
 * 
 * 1. dfs를 이용하여 최소 시간 구하기
 * 2. 0, 0부터 시작하여 상하좌우로 이동하여 맵을 이동한다.
 * 3. 이때 방문 표시는 해당 위치에 도착했을 때 걸린 시간을 입력한다.
 * 4. 만약 그 위치에 저장된 시간 값보다 더 빠르게 그 위치에 도달했다면 해당 위치를 방문하고 위치값을 갱신한다.
 * 5. 큐가 빌 경우 함수를 종료하며, 최소 시간을 출력한다.
 * 
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int size;
	public static int[][] map;
	public static int[][] visited;
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	public static int result;
	
	public static void findShortestPath() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0,0,0});
		
		while(!queue.isEmpty()) {
			int[] current  = queue.poll();
			
			if(current[0] == size-1 && current[1] == size-1) {
				result = Math.min(result, current[2]);
				continue;
			}
			
			if(result < current[2]) {
				continue;
			}
			
			for (int delta = 0; delta < 4; delta++) {
				int row = current[0] + dx[delta];
				int col = current[1] + dy[delta];
				
				if(row < 0 || col < 0 || row >= size || col >= size || visited[row][col] <= current[2]) {
					continue;
				}
				
				visited[row][col] = current[2];
				queue.offer(new int[] {row, col, current[2]+map[row][col]});
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			size = Integer.parseInt(br.readLine().trim());
			map = new int[size][size];
			visited = new int[size][size];
			result = Integer.MAX_VALUE;

			for (int row = 0; row < size; row++) {
				String mapStr = br.readLine().trim();	
				for (int col = 0; col < size; col++) {
					map[row][col] = mapStr.charAt(col) - '0';
				}
				Arrays.fill(visited[row], 10001);
			}
			visited[0][0] = 0;
			findShortestPath();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
