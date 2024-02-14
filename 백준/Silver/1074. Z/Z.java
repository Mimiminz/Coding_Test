import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1074 Z
 * @author JOMINJU
 * 
 *	1. 함수 안에서 Z의 형태로 row값과 col값을 변경시킨다
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
			if(rowNum == 0 && colNum == 0) {
				sb.append(count);
			}else if(rowNum == 1 && colNum == 0) {
				sb.append(count+1);
			}else if(rowNum == 0 && colNum == 1) {
				sb.append(count+2);
			}else {
				sb.append(count+3);
			}
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
		int size = (int) Math.pow(2, N);
		findLocation(size, 0);
		System.out.println(sb);
		br.close();
	}
}