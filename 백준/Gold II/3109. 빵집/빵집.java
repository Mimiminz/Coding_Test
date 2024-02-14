import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * BOJ 3109 빵집
 * @author JOMINJU
 *
 * 1. 첫번째 행부터 시작해서 마지막 행까지 반복한다.
 * 2. 순서대로 오른쪽 위, 오른쪽, 오른쪽 아래를 확인한다.
 * 3. 만약 이미 방문했거나(파이프 설치) X표시로 건물이 있는 공간이라면 해당 함수를 빠져나온다.
 * 4. 마지막 열에 도달했다면 해당 위치를 방문표시하고, 함수를 종료한다.
 * 5. 만약 도달하지 못하고 함수가 종료됐다면 파이프 설치 표시를 제거한다.
 */

public class Main{
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static boolean[][] town;
	public static int pipeNum;
	public static int rowNum;
	public static int colNum;
	public static int flag;
	
	public static int[] dx = {-1, 0, 1};
	
	public static void pipe(int row, int col) {		
		town[row][col] = true;
		
		for(int idx = 0; idx < 3; idx++) {
			int currentRow = dx[idx] + row;
			int currentCol = 1 + col;
			
			if(currentRow < 0 || currentCol < 0 || currentRow >= rowNum || currentCol >= colNum)
				continue;
			
			if(town[currentRow][currentCol] == false) {				

				if(currentCol == colNum-1) {
					flag = 1;
					pipeNum++;
					return;
				}
				
				pipe(currentRow, currentCol);
				if(flag == 1)
					return;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		rowNum = Integer.parseInt(st.nextToken());
		colNum = Integer.parseInt(st.nextToken());
		town = new boolean[rowNum][colNum];
		
		for(int row = 0; row < rowNum; row++) {
			String str = br.readLine().trim();
			for(int col = 0; col < colNum; col++) {
				if(str.charAt(col) != '.')
					town[row][col] = true;
			}
		}
		pipeNum = 0;
		for(int row = 0; row < rowNum; row++) {
			flag = 0;
			pipe(row, 0);
		}
		sb.append(pipeNum);
		System.out.println(sb);
		br.close();
	}
}