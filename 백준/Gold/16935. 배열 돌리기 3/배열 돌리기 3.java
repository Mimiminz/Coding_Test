import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * BOJ 16935 배열 돌리기 3
 * @author JOMINJU
 * 
 *	1. 배열의 크기과 연산의 수, 배열을 입력받는다.
 *	2. 수행해야하는 연산에 따라 해당 번호에 맞는 연산을 수행시킨다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] matrix;
	public static int size;
	public static int colSize;
	public static int rowSize;
	
	static int[][] upDown(int[][] origin) {
		int[][] temp = new int[size][size];
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				temp[colSize - col -1][row] = origin[col][row];
			}
		}
		return temp;
	}
	
	static int[][] leftRight(int[][] origin) {
		int[][] temp = new int[size][size];
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				temp[col][rowSize - row - 1] = origin[col][row];
			}
		}
		return temp;
	}
	
	static int[][] right90(int[][] origin) {
		int aaa = colSize;
		colSize = rowSize;
		rowSize = aaa;
		int[][] temp = new int[size][size];
		for(int col = 0; col < size; col++) {
			for(int row = 0; row < size; row++) {
				if(col >= colSize || row >= rowSize)
					temp[col][row] = 0;
				else
					temp[col][row] = origin[rowSize - row - 1][col];
			}
		}
		return temp;
	}
	
	static int[][] left90(int[][] origin) {
		int aaa = colSize;
		colSize = rowSize;
		rowSize = aaa;
		int[][] temp = new int[size][size];
		for(int col = 0; col < size; col++) {
			for(int row = 0; row < size; row++) {
				if(col >= colSize || row >= rowSize)
					temp[col][row] = 0;
				else
					temp[col][row] = origin[row][colSize - col -1];
			}
		}
		return temp;
	}
	
	static int[][] clock(int[][] origin) {
		int[][] temp = new int[size][size];
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				// 1번 -> 2번
				if(col < colSize/2 && row < rowSize/2)
					temp[col][row + rowSize/2] = origin[col][row];
				// 2번 -> 3번
				else if(col < colSize/2 && row >= rowSize/2)
					temp[col + colSize/2][row] = origin[col][row];
				// 3번 -> 4번
				else if(col >= colSize/2 && row >= rowSize/2)
					temp[col][row -rowSize/2] = origin[col][row];
				// 4번 -> 1번
				else
					temp[col - colSize/2][row] = origin[col][row];
			}
		}
		return temp;
	}
	
	static int[][] reverseClock(int[][] origin) {
		int[][] temp = new int[size][size];
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				// 1번 -> 4번
				if(col < colSize/2 && row < rowSize/2)
					temp[col + colSize/2][row] = origin[col][row];
				// 2번 -> 1번
				else if(col < colSize/2 && row >= rowSize/2)
					temp[col][row -rowSize/2] = origin[col][row];
				// 3번 -> 2번
				else if(col >= colSize/2 && row >= rowSize/2)
					temp[col - colSize/2][row] = origin[col][row];
				// 4번 -> 3번
				else
					temp[col][row + rowSize/2] = origin[col][row];
			}
		}
		return temp;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		size = colSize >= rowSize ? colSize : rowSize;
		int rotationNum = Integer.parseInt(st.nextToken());
		matrix = new int[size][size];
		
		for(int col = 0; col < colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < rowSize; row++) {
				matrix[col][row] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < rotationNum; idx++) {
			char rotation = st.nextToken().charAt(0);
			switch(rotation) {
				case '1':
					matrix = upDown(matrix);
					break;
				case '2':
					matrix = leftRight(matrix);
					break;
				case '3':
					matrix = right90(matrix);
					break;	
				case '4':
					matrix = left90(matrix);
					break;
				case '5':
					matrix = clock(matrix);
					break;
				case '6':
					matrix = reverseClock(matrix);
					break;
			}
		}
		for(int col = 0; col < size; col++) {
			if(matrix[col][0] == 0)
				continue;
			for(int row = 0; row < size; row++) {
				if(matrix[col][row] == 0)
					break;
				sb.append(matrix[col][row]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
