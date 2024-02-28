import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 17070 파이프 옮기기1
 * @author JOMINJU
 * 
 * dfs로 했더니 시간초과가 떴다..
 * python일 경우를 제외하고 dfs로 풀면 시간초과가 난다고 한다...
 * 눈물을 머금고 수정
 *
 * 1. 파이프의 시작 위치는 1,2에서 시작한다.
 * 2. type 0 : 가로, type : 1 세로, type : 2는 대각선으로 간주한다.
 * 3. 이차원 배열을 돌며 가로 방향일 경우에는 가로 방향만, 세로는 세로 방향만, 대각선은 가로 세로 전부 1이 아닌지 확인하고 더해준다.
 * 4. 배열의 제일 마지막 값을 출력한다. 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int homeSize;
	public static int[][] home;
	
	
	public static void movePipe() {
		int[][][] check = new int[homeSize+1][homeSize+1][3];
		
		check[1][2][0] = 1;
		
		// row, col, type
		// 이때 type은 0이 가로, 1이 세로, 2가 대각선 방향이다.
		for (int col = 3; col <= homeSize; col++) {
			if(home[1][col] == 0)
				check[1][col][0] = check[1][col-1][0];
		}
		
		for (int row = 2; row <= homeSize; row++) {
			for (int col = 3; col <= homeSize; col++) {
				
				// 벽에 막힐 경우 패스
				if(home[row][col] == 1)
					continue;
				
				// 가로로 오는 경우
				if(home[row][col - 1] == 0)
					check[row][col][0] = check[row][col-1][0] + check[row][col-1][2];
				
				// 세로로 오는 경우
				if(home[row-1][col] == 0)
					check[row][col][1] = check[row-1][col][1] + check[row-1][col][2];

				// 대각선일 경우, 이동 방향에서 모든 타입이 전부 가능하므로 전부 더한다.
				if(home[row-1][col] == 0 && home[row][col - 1] == 0)
					check[row][col][2] = check[row-1][col-1][0] + check[row-1][col-1][1] + check[row-1][col-1][2];
			}
		}
		sb.append(check[homeSize][homeSize][0] + check[homeSize][homeSize][1] + check[homeSize][homeSize][2]);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		homeSize = Integer.parseInt(br.readLine().trim());
		home = new int[homeSize+1][homeSize+1];
		
		for (int row = 1; row <= homeSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= homeSize; col++) {
				home[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		movePipe();
		
		System.out.println(sb);
		br.close();
	}
}
