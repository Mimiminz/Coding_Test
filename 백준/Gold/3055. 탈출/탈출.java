
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 3055 탈출
 * @author JOMINJU
 *
 * 1. 물의 시작 위치와 고슴도치의 위치를 각각 큐에 집어넣는다.
 * 2. 물 먼저 상하좌우를 돌면서 비어있는 칸을 물로 채운다.
 * 3. 물이 먼저 이동한 후, 고슴도치가 상하좌우에 비어있는 곳으로 이동한 뒤 큐에 집어넣는다.
 * 4. 고슴도치의 큐가 빌 때까지 반복한다.
 * 5. 만약 비기 전까지 목적지에 도착한다면 시간을 출력한다.
 * 6. 목적지에 도착하기 전에 고슴도치의 큐가 빈다면 kaktus를 출력한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static char[][] forest;
	
	public static Queue<int[]> hedgehog;
	public static Queue<int[]> water;
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	public static boolean moveHedgehog() {
		int cnt = hedgehog.size();
		
		for (int idx = 0; idx < cnt; idx++) {
			int[] curHedgehog = hedgehog.poll();
			
			for (int delta = 0; delta < 4; delta++) {
				int row = curHedgehog[0] + dx[delta];
				int col = curHedgehog[1] + dy[delta];
				
				if(row < 0 || col < 0 || row >= rowSize || col >= colSize) {
					continue;
				}
				
				if(forest[row][col] == '.') {
					hedgehog.add(new int[] {row, col});
				}else if(forest[row][col] == 'D') {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static int moveToCave() {
		int count = 0;
		while(!hedgehog.isEmpty()) {
			int cnt = water.size();
			count++;
			
			for (int idx = 0; idx < cnt; idx++) {
				int[] curWater = water.poll();
				
				for (int delta = 0; delta < 4; delta++) {
					int row = curWater[0] + dx[delta];
					int col = curWater[1] + dy[delta];
					
					if(row < 0 || col < 0 || row >= rowSize || col >= colSize) {
						continue;
					}
					
					if(forest[row][col] == '.') {
						forest[row][col] = '*';
						water.add(new int[] {row, col});
					}
				}
			}
			
			int cnt2 = hedgehog.size();
			
			for (int idx = 0; idx < cnt2; idx++) {
				int[] curHedgehog = hedgehog.poll();
				
				for (int delta = 0; delta < 4; delta++) {
					int row = curHedgehog[0] + dx[delta];
					int col = curHedgehog[1] + dy[delta];
					
					if(row < 0 || col < 0 || row >= rowSize || col >= colSize) {
						continue;
					}
					
					if(forest[row][col] == '.') {
						forest[row][col] = 'S';
						hedgehog.add(new int[] {row, col});
					}else if(forest[row][col] == 'D') {
						return count;
					}
				}
			}
			
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		forest = new char[rowSize][colSize];
		hedgehog = new LinkedList<>();
		water = new LinkedList<>();
		
		for (int row = 0; row < rowSize; row++) {
			String rowStr = br.readLine().trim();
			for (int col = 0; col < colSize; col++) {
				forest[row][col] = rowStr.charAt(col);
				
				if(forest[row][col] == 'S') {
					hedgehog.add(new int[] {row, col});
				}else if(forest[row][col] == '*') {
					water.add(new int[] {row, col});
				}
			}
		}
		
		int result = moveToCave();
		
		if(result == -1) {
			sb.append("KAKTUS");
		}else {
			sb.append(result);			
		}
		
		System.out.println(sb);
		
		return;
	}
}
