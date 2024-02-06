import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 16926 배열 돌리기1
 * @author JOMINJU
 * 
 * 1. 배열의 크기와 정수 R을 입력받는다.
 * 2. 기존 배열을 입력받는다.
 * 3. 제일 겉의 배열부터 안으로 들어갈 때 마다 길이가 -2 씩 줄어든다.
 * 4. start와 end 값을 정해 각 범위에 따라 배열이 회전할 수 있도록 한다.
 * 5. out를 0부터 배열의 크기만큼 돌린다.
 * 6. 내부 반복문을 in = start에서 시작해 end까지 돌린다.
 * 7. 만약 out 값이 절반이 안될 경우
 * 		7-1. [out][in]은 왼쪽으로 이동
 * 		7-2. [in][out]은 아래로 이동한다.
 * 8. out 값이 절반을 넘은 경우
 * 		8-1. [out][in]은 오른쪽으로 이동
 * 		8-2. [in][out]은 위로 이동한다.
 * 9. 만약 이동한 값이 start보다 작아지거나 end 보다 커질 경우
 * 
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	// 상 좌 하 우 -> 상 부터 시계반대 방향
	public static int[] dx = {0, -1, 0, 1};
	public static int[] dy = {-1, 0, 1, 0};
	
	public static int[][] origin;
	public static int[][] change;
	
	public static int colSize;
	public static int rowSize;
	public static int rotation;
	
	public static void move(int col, int row, int direction, int count) {
		//System.out.printf("count : " + count + " origin : " + origin[col][row] + " : ");
		//System.out.printf(" col: " + col + " row : " + row + " direct : " + direction);
		int originNum = origin[col][row];
		for(int idx = 0; idx < rotation; idx++) {
			if(direction == 1 || direction == 3) {
				if(row + dx[direction] < count || row + dx[direction] >= rowSize - count) {
					direction = (direction+1)%4;
				}
			}else {
				if(col + dy[direction] < count || col + dy[direction] >= colSize - count) {
					direction = (direction+1)%4;
				}
			}
			row += dx[direction];
			col += dy[direction];
		}
		//System.out.println(col + ", " + row + " direct : " + direction);
		change[col][row] = originNum;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		// 1. 배열의 크기와 정수 R을 입력받는다.
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		rotation = Integer.parseInt(st.nextToken());
		origin = new int[colSize][rowSize];
		change = new int[colSize][rowSize];
		int start = 0;
		int end = 0;

		// 2. 기존 배열을 입력받는다.
		for(int col = 0; col < colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < rowSize; row++)
				origin[col][row] = Integer.parseInt(st.nextToken());
		}
		// 좌 우 이동	
		start = 0;
		end = rowSize;
				
		for(int col = 0; col < colSize; col++) {
			for(int row = start; row < end; row++) {
				if(col < colSize/2) {
					if(row - rotation < start)
						move(col, row, 1, start);
					else {	// 배열을 넘어가지 않을 경우
						change[col][row-rotation] = origin[col][row];
					}
				}else if(col >= colSize/2) {
					if(row + rotation >= end)
						move(col, row, 3, start);
					else
						change[col][row+rotation] = origin[col][row];
				}
			}
			if(col < colSize/2 - 1) {
				start++;
				end--;
			}else if((col >= colSize/2 && colSize%2 == 0) || ((col > colSize/2 && colSize%2 != 0))){
				start--;
				end++;
			}
		}
		
		// 상 하 이동
		start = 0;
		end = colSize;
		for(int row = 0; row < rowSize; row++) {
			for(int col = start; col < end; col++) {
				// 가로 절반 나누고 왼쪽일 때 아래로 내려감
				if(row < rowSize/2) {
					if(col + rotation >= end)
						move(col, row, 2, start);
					else	// 배열을 넘어가지 않을 경우
						change[col+rotation][row] = origin[col][row];
					
				}// 가로 절반 나누고 오른쪽일 때 위로 올라감
				else if(row >= rowSize/2){
					if(col - rotation < start)
						move(col, row, 0, start);
					else
						change[col-rotation][row] = origin[col][row];
				}
			}
			if(row < rowSize/2 -1 ) {
				start++;
				end--;
			}else if((row >= rowSize/2 && rowSize%2 == 0) || (row > rowSize/2 && rowSize%2 != 0)){
				start--;
				end++;
			}
		}
		// 출력
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++)
				sb.append(change[col][row]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}