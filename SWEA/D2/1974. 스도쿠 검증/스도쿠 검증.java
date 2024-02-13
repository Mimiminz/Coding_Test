import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] sudoku = new int[9][9];
	public static int visited;
	public static int total = 45;
	
	public static int checkRow() {
		for(int col = 0; col < 9; col++) {
			visited = 0;
			for(int row = 0; row < 9; row++)
				visited += sudoku[col][row];
			if(visited != total)
				return 0;
		}
		return 1;
	}
	
	public static int checkCol() {
		for(int row = 0; row < 9; row++) {
			visited = 0;
			for(int col = 0; col < 9; col++)
				visited += sudoku[col][row];
			if(visited != total)
				return 0;
		}
		return 1;
	}
	
	public static int checkBox() {	
		for (int col = 0; col < 9; col+=3) {
			for (int row = 0; row < 9; row+=3) {
				visited = 0;
				for (int y = 0; y < 3; y++)
					for (int x = 0; x < 3; x++)
						visited += sudoku[col+y][row+x];
				if(visited != total)
					return 0;
			}					
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			for(int col = 0; col < 9; col++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int row = 0; row < 9; row++)
					sudoku[col][row] = Integer.parseInt(st.nextToken());
			}
			sb.append("#").append(tc).append(" ");
			if(checkRow() == 0 || checkCol() == 0 || checkBox() == 0)
				sb.append(0).append("\n");
			else
				sb.append(1).append("\n");
		}
		System.out.println(sb);
        br.close();
	}
}
