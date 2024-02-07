import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 2563 색종이
 * @author JOMINJU
 * 
 * 도화지의 넓이는 100x100, 색종이의 넓이는 10x10
 * 
 *	1. 색종이의 수와 떨어진 위치를 입력받는다.
 *	2. 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public final static int MAX_SIZE = 100;
	
	public static boolean[][] location;
	
	public static int paintColor(int row, int col, int size) {
		for(int y = col; y < col + 10; y++) {
			for(int x = row; x < row + 10; x++) {
				if(!location[y][x]) {
					location[y][x] = true;
					size++;
				}
			}
		}
		return size;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int num = Integer.parseInt(br.readLine().trim());
		location = new boolean[MAX_SIZE][MAX_SIZE];
		int size = 0;
		for(int idx = 0; idx < num; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			size = paintColor(row, col, size);
		}
		sb.append(size);
		System.out.println(sb);
	}
}
