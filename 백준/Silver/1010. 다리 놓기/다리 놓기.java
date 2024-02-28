import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1010 다리놓기
 * @author JOMINJU
 *
 * 1. bridges[서쪽의 연결할 다리 번호][동쪽의 연결한 다리 번호] 의 배열을 선언한다.
 * 2. 다리가 겹쳐질 수 없기 때문에 동쪽의 다리 번호는 서쪽를 시작점으로 계산한다.
 * 3. 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int westCount = Integer.parseInt(st.nextToken());
			int eastCount = Integer.parseInt(st.nextToken());
			int[][] bridges = new int[eastCount+1][westCount+1];
			
			
			
			
			for (int east = 0; east <= eastCount; east++) {
				for (int west = 0, end = Math.min(east, westCount); west <= end; west++) {
					if(west == 0 || west == east) 
						bridges[east][west] = 1;						
					else
						bridges[east][west] = bridges[east-1][west-1] + bridges[east-1][west];
				}
			}
			sb.append(bridges[eastCount][westCount]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}