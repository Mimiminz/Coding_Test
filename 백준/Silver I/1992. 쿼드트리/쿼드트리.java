import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] video;
	
	public static void QuadTree(int row, int col, int size) {
		
		//압축이 되는 경우
		if(Compression(row, col, size)) {
			sb.append(video[row][col]);
			return;
		}
		
		sb.append("(");
		int newSize = size/2;
		QuadTree(row, col, newSize);
		QuadTree(row, col+newSize, newSize);
		QuadTree(row+newSize, col, newSize);
		QuadTree(row+newSize, col+newSize, newSize);
		sb.append(")");
	}
	
	public static boolean Compression(int row, int col, int size) {
		int check = video[row][col];
		for(int newRow = row; newRow < row+size; newRow++) {
			for(int newCol = col; newCol < col+size; newCol++) {
				if(check != video[newRow][newCol])
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int size = Integer.parseInt(br.readLine().trim());
		video = new int[size][size];
		for(int row = 0; row < size; row++) {
			String str = br.readLine().trim();
			for(int col = 0; col < size; col++)
				video[row][col] = str.charAt(col) - '0';
		}
		
		QuadTree(0, 0, size);
		System.out.println(sb);
	}
}