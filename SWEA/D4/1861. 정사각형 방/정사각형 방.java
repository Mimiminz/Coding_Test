import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1891 정사각형 방
 * @author JOMINJU
 * 
 * 1. 정수와 방의 번호를 입력받는다.
 * 2. 반복문을 돌며 처음부터 마지막 방까지 이동할 수 있는 방의 개수를 구한다.
 * 3. dfs를 통해 최대 이동 수를 구한다.
 * 		3-1. 상하좌우에 방이 없거나, 1보다 큰 방이 없을 경우 함수를 종료한다.
 * 		3-2. 만약 방이 존재하는 경우 dfs를 호출하여 카운트를 1 늘리고 다음 방으로 이동한다.
 *		3-3. 이동한 방은 visited를 1로 바꿔 다시 이동하지 않게 막는다.
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] rooms;
	public static int[][] visited;
	public static int result;
	public static int dfsResult;
	public static int size;
	
	// 상 하 좌 우
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	// 3. dfs를 통해 최대 이동 수를 구한다.
	public static void dfs(int col, int row, int count) {
		if(count >= result) {
			dfsResult = count;
		}
		for(int direct = 0; direct < 4; direct++) {
			int drow = row + dx[direct];
			int dcol = col + dy[direct];
			
			if(drow < 0 || drow >= size || dcol < 0 || dcol >= size)
				continue;
			
			if(visited[dcol][drow] == 0 && rooms[dcol][drow] == rooms[col][row] + 1) {
				visited[dcol][drow] = 1;
				dfs(dcol, drow, count+1);
				visited[dcol][drow] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 정수와 방의 번호를 입력받는다.
			size = Integer.parseInt(br.readLine().trim());
			result = 0;
			int currentRoomNumber = Integer.MAX_VALUE;
			rooms = new int[size][size];
			visited = new int[size][size];
			
			for(int col = 0; col < size; col++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int row = 0; row < size; row++) {
					rooms[col][row] = Integer.parseInt(st.nextToken());
				}
			}
			// 2. 반복문을 돌며 처음부터 마지막 방까지 이동할 수 있는 방의 개수를 구한다.
			for(int col = 0; col < size; col++) {
				for(int row = 0; row < size; row++) {
					dfsResult = 0;
					dfs(col, row, 1);
					if(dfsResult > result || (dfsResult == result && rooms[col][row] < currentRoomNumber )) {
						currentRoomNumber = rooms[col][row];
						result = dfsResult;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(currentRoomNumber).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}