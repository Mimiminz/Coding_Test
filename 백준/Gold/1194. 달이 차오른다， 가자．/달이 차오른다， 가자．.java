
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1194 달이 차오른다, 가자.
 * @author JOMINJU
 *
 *	A = 65, a = 97
 *
 * 1. 미로의 크기, 미로의 정보를 입력받는다.
 * 2. 미로에서 0이 위치한 정보는 민식이의 시작점으로 정보를 저장한다.
 * 3. 미로를 돌면서 . 이거나 알파벳 소문자일 경우는 이동한다.
 * 		3-1. 이때 알파벳 소문자가 있을 경우, 비트마스킹을 이용해 정보를 저장한다.
 * 4. 만약 문(알파벳 대문자)가 있을 경우, 비트마스킹으로 저장한 열쇠의 정보가 있을 경우에만 지나간다.
 * 5. bfs를 이용해 이동하면서 출구(1)를 찾을 경우 함수를 종료한다.
 * 
 * 방문확인은 비트마스킹으로 구한 키 값을 저장해서 확인한다.
 * 이미 지나간 곳인데, 다른 열쇠를 구한 뒤 다시 지나가면 문을 열 수 있을 것이기에 지나가고
 * (이때 열쇠를 가지고 있더라도 다른 열쇠가 아닌 경우에는 지나가지 않는다)
 * 열쇠를 구하지 못한채로 들어가면 어차피 똑같을 것이기에 지나가지 않는다.
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static int[] start;
	public static int[][] visited;
	public static char[][] maze;
	
	public static boolean[] key;
	
	public static int answer;
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	public static void findExit() {
		Queue<int[]> queue = new LinkedList<>();
		// 시작 row, 시작 col, 가지고 있는 키, 이동횟수
		queue.add(new int[] {start[0], start[1], 0, 0});
		
		for (int idx = 0; idx < rowSize; idx++) {
			Arrays.fill(visited[idx], -1);
		}
		
		visited[start[0]][start[1]] = 0;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int curRow = current[0];
			int curCol = current[1];
			int key = current[2];
			int move = current[3];
			
			//System.out.println("Row : " + curRow + ", Col : " + curCol);
			
			for (int direct = 0; direct < 4; direct++) {
				int row = curRow + dx[direct];
				int col = curCol + dy[direct];
				
				if(row < 0 || col < 0 || row >= rowSize || col >= colSize) {
					continue;
				}
				
				if(visited[row][col] != -1 && (key & (~visited[row][col])) == 0) {
					continue;
				}
				
				// 출구를 발견한 경우
				if(maze[row][col] == '1') {
					answer = Math.min(answer, move+1);
					return;
				}// 막힌 벽일 경우
				else if(maze[row][col] == '#') {
					continue;
				}// 키를 발견한 경우
				else if(maze[row][col] >= 97) {
					int newKey = key | (1<<(maze[row][col]-96));
					visited[row][col] = newKey;
					queue.add(new int[] {row, col, newKey, move+1});
				}// 문을 발견한 경우
				else if(maze[row][col] >= 65) {
					int checkKey = maze[row][col]-64;
					if((key & (1<<checkKey)) == (1<<checkKey)) {
						visited[row][col] = key;
						queue.add(new int[] {row, col, key, move+1});
					}
				}// 그 외 그냥 이동 가능한 경우
				else {
					visited[row][col] = key;
					queue.add(new int[] {row, col, key, move+1});
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		key = new boolean[6];
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		visited = new int[rowSize][colSize];
		maze = new char[rowSize][colSize];
		answer = Integer.MAX_VALUE;
		
		for (int row = 0; row < rowSize; row++) {
			String mazeStr = br.readLine().trim();
			for (int col = 0; col < colSize; col++) {
				maze[row][col] = mazeStr.charAt(col);
				if(maze[row][col] == '0') {
					start = new int[] {row, col};
				}
			}
		}
		
		findExit();
		if(answer == Integer.MAX_VALUE) {
			sb.append(-1);
		}else {
			sb.append(answer);
		}
		
		System.out.println(sb);
		br.close();
		
	}
}
