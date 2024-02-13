import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 1987 알파벳
 * @author JOMINJU
 *
 *	1. 캐릭터 값을 board 변수에 집어넣는다.
 *	2. dfs에서 만약 해당 위치의 값이 알파벳에 들어있지 않다면, 알파벳에 값을 넣고 해당 위치의 row, col에서 다시 dfs를 선언한다.
 *	3. dfs가 종료된다면 해당 위치의 값을 알파벳에서 제거한다.
 *	4. 이를 반복하며 제일 큰 current값을 찾는다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static char[][] board;
	public static int result;
	public static List<Character> alphabet;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int current, int col, int row) {
		result = Math.max(current, result);
		
		for(int direct = 0; direct < 4; direct++) {
			int currentX = dx[direct] + row;
			int currentY = dy[direct] + col;
			int alphaLength = alphabet.size();
			if(currentX >= 0 && currentY >= 0 && currentX < rowSize && currentY < colSize
					&& !alphabet.contains(board[currentY][currentX])) {
				alphabet.add(board[currentY][currentX]);
				dfs(current+1, currentY, currentX);
				alphabet.remove(alphaLength);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		board = new char[colSize][rowSize];
		result = 1;
		
		alphabet = new ArrayList<>();
		
		for(int col = 0; col < colSize; col++) {
			String rowStr = br.readLine().trim();
			for(int row = 0; row < rowStr.length(); row++) {
				board[col][row] = rowStr.charAt(row);
			}
		}
		alphabet.add(board[0][0]);
		dfs(1,0,0);
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
}