import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 10026 적록색약
 * @author JOMINJU
 * 
 *  1. 
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static boolean isBlind;
	public static int size;
	public static char[][] board;
	public static boolean[][] visited;
	
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	
	public static void redGreen(int row, int col) {

		visited[row][col] = true;

		for (int direct = 0; direct < 4; direct++) {
			int nx = row + dx[direct];
			int ny = col + dy[direct];
			
			if(nx < 0 || ny < 0 || nx >= size || ny >= size)
				continue;
			
			if(visited[nx][ny])
				continue;
			
			if(!isBlind && board[row][col] == board[nx][ny]) {
				redGreen(nx, ny);
			}
			
			if(isBlind) {
				if(board[row][col] == board[nx][ny]) {
					redGreen(nx, ny);
				}else if(board[row][col] != 'B' && board[nx][ny] != 'B') {
					redGreen(nx, ny);
				}
			}		
			
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		size = Integer.parseInt(br.readLine().trim());
		board = new char[size][size];
		int group = 0;
		
		for (int row = 0; row < size; row++) {
			String rowStr = br.readLine().trim();
			for (int col = 0; col < size; col++) {
				board[row][col] = rowStr.charAt(col);
			}
		}
		
		isBlind = false;
		visited = new boolean[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if(!visited[row][col])	{
					group++;
					redGreen(row, col);
				}
			}
		}
		sb.append(group).append(" ");
		group = 0;
		isBlind = true;
		visited = new boolean[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if(!visited[row][col]) {
					group++; 
					redGreen(row, col);
				}
			}
		}
		
		sb.append(group);
		System.out.println(sb);
	}
}
