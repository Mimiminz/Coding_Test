
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 2239 스도쿠
 * @author JOMINJU
 *
 * 1. 0이 아닌 값을 입력받았을 때, 해당 row와 row가 일치하는 모든 값, 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] sudoku;
	public static List<int[]> zero;

	
	public static void solveSudoku(int level) {
		if(zero.size() == level) {
			for (int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					sb.append(sudoku[row][col]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		int row = zero.get(level)[0];
		int col = zero.get(level)[1];
		
		boolean[] check = new boolean[10];
		
		// 가로 세로 체크
		for (int idx = 0; idx < 9; idx++) {
			if(sudoku[idx][col] != 0) {				
				check[sudoku[idx][col]] = true;
			}
			
			if(sudoku[row][idx] != 0) {
				check[sudoku[row][idx]] = true;				
			}
		}
		
		// 사각형 체크
		for (int drow = row/3 * 3; drow < row/3 * 3 + 3; drow++) {
			for (int dcol = col/3 * 3; dcol < col/3 * 3 + 3; dcol++) {
				if(sudoku[drow][dcol] != 0) {
					check[sudoku[drow][dcol]] = true;				
				}
			}
		}
		
		for (int idx = 1; idx < 10; idx++) {
			if(!check[idx]) {
				sudoku[row][col] = idx;
				solveSudoku(level+1);
				sudoku[row][col] = 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		sudoku = new int[9][9];
		zero = new ArrayList<>();
		
		for (int row = 0; row < 9; row++) {
			String rowStr = br.readLine().trim();
			for (int col = 0; col < 9; col++) {
				sudoku[row][col] = rowStr.charAt(col) - 48;
				if(sudoku[row][col] == 0) {
					zero.add(new int[] {row, col});
				}
			}
		}

		solveSudoku(0);
	}
}
