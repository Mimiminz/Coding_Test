import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1600 말이 되고픈 원숭이
 * @author JOMINJU
 *
 * 1. 원숭이가 움직이는 최소 횟수를 구해야 한다.
 * 2. 원숭이가 말의 움직임을 따라할 수 있는 횟수, 보드판의 상태를 입력받는다.
 * 3. bfs를 이용하여 원숭이를 이동시킨다.
 * 4. 0,0부터 시작하여 원숭이의 x좌표, y좌표, 횟수, 말 이동 횟수를 큐에 넣는다.
 * 5. 먼저 말 이동 횟수를 사용하여 이동한 좌표, 횟수+1, 말 모방 여부에 따라 횟수를 -1시킨 후 다시 큐에 집어넣는다.
 * 6. 가장 먼저 도착지점에 도착한 큐의 횟수를 출력한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static int[][] board;
	public static boolean[][][] visited;
	
	// 말 이동 경우 8가지, 우하좌상 4가지
	// 오른쪽 아래에서 시계방향으로 이동
	public static int[] drx = {2, 1, -1, -2, -2, -1, 1, 2};
	public static int[] dry = {1, 2, 2, 1, -1, -2, -2, -1};
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	
	public static int moveMonkey(int imitate) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0, 0, imitate});
		
		int[] monkey = new int[4];
		while(!queue.isEmpty()) {
			monkey = queue.poll();
			int count = monkey[2];
			int currentImitate = monkey[3];
			
			if(count > rowSize*colSize)
				break;
			
			if(monkey[0] == rowSize-1 && monkey[1] == colSize-1) {
				return count+1;
			}
			
			if(currentImitate > 0) {
				for (int direct = 0; direct < 8; direct++) {
					int nx = monkey[0] + drx[direct];
					int ny = monkey[1] + dry[direct];
					
					if(nx < 0 || ny < 0 || nx >= rowSize || ny >=  colSize)
						continue;
					
					// 벽으로 막혀있거나 이미 방문했다면
					if(board[nx][ny] == 1 || visited[nx][ny][currentImitate-1])
						continue;
					
					if(nx == rowSize-1 && ny == colSize-1) {
						return count+1;
					}
					
					visited[nx][ny][currentImitate-1] = true;	
					queue.offer(new int[] {nx, ny, count+1, currentImitate-1});
				}
			}		
			for (int direct = 0; direct < 4; direct++) {
				int nx = monkey[0] + dx[direct];
				int ny = monkey[1] + dy[direct];
				
				if(nx < 0 || ny < 0 || nx >= rowSize || ny >=  colSize)
					continue;
				
				// 벽으로 막혀있거나 현재 방문 횟수보다 크다면 패스
				if(board[nx][ny] == 1 || visited[nx][ny][currentImitate])
					continue;
				
				if(nx == rowSize-1 && ny == colSize-1) {
					return count+1;
				}
				
				visited[nx][ny][currentImitate] = true;	
				queue.offer(new int[] {nx, ny, count+1, currentImitate});
				
			}
			
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int imitate = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize][imitate+1];
		
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		visited[0][0][imitate] = true;
		
		if(rowSize == 1 && colSize == 1)
			sb.append(0);
		else
			sb.append(moveMonkey(imitate));
		System.out.println(sb);
		br.close();
	}
}