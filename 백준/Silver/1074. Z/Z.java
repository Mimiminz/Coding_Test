import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1074 Z
 * @author JOMINJU
 * 
 *	1. 크기의 절반만큼 나눴을 때 왼쪽 상단, 오른쪽 상단, 왼쪽 하단, 오른쪽 하단에 따라 카운트의 값을 변경시킨다.
 *	2. 이후 구해야하는 row와 col의 값도 이동한 만큼 감소시킨다.
 *	3. 하나의 Z를 가질 수 있는, 크기가 2인 값이 될때까지 반복한다.
 *	4. 크기가 2일 경우 row와 col의 위치에 따라 카운트 값을 변경시킨다.
 *	2. 이후 2의 n-1의 제곱이 될 때마다 재귀를 호출시킨다
 *	3. 입력받은 row, col을 몇번째로 방문하는지 확인한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int N;
	public static int rowNum;
	public static int colNum;
	
	public static void findLocation(int currentN, int count) {
		if(currentN == 2) {
			if(rowNum == 1 && colNum == 0) {
				count += 1;
			}else if(rowNum == 0 && colNum == 1) {
				count += 2;
			}else if(rowNum == 1 && colNum == 1){
				count += 3;
			}
			sb.append(count);
			return;
		}
		
		if(colNum < currentN/2 && rowNum >= currentN/2) {
			count += currentN * currentN / 4;
			rowNum -= currentN/2;
		}else if(colNum >= currentN/2 && rowNum < currentN/2) {
			count += currentN * currentN / 2;
			colNum -= currentN/2;
		}else if(colNum >= currentN/2 && rowNum >= currentN/2) {
			count += currentN * currentN / 4 * 3;
			rowNum -= currentN/2;
			colNum -= currentN/2;
		}
		findLocation(currentN/2, count);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		colNum = Integer.parseInt(st.nextToken());
		rowNum = Integer.parseInt(st.nextToken());
		findLocation((int) Math.pow(2, N), 0);
		System.out.println(sb);
		br.close();
	}
}