import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
 *  BOJ 신기한 소수
 *  @author JOMINJU
 *  
 *  1. 해당 숫자가 소수인지 판별하는 함수를 작성한다.
 *  2. 소수가 아니라면 함수를 즉시 리턴한다.
 *  3. 소수라면 다음수를 넣고 다음 자리 소수 판별을 시작한다.
 *  4. N번째 자리까지 도달한다면, 값을 리턴하고 함수를 종료한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static int maxNumber;
	
	public static void checkDecimal(int current) {
		
		for(int idx = 1; idx < 10; idx++) {
			int checkNum = current*10 + idx;
			int flag = 0;
			if(checkNum == 1)	continue;
			
			for(int check = 2; check <= (checkNum >> 1); check++) {
				if(checkNum % check == 0) {
	            	// 2. 소수가 아니라면 함수를 즉시 리턴한다.
					flag = 1;
					break;
				}
			}
			if(flag == 1)
				continue;
			
			// 4. N번째 자리까지 도달한다면, 값을 리턴하고 함수를 종료한다.
			if(Integer.toString(checkNum).length() == maxNumber) {
				sb.append(checkNum).append("\n");
				continue;
			}
			// 3. 소수라면 다음수를 넣고 다음 자리 소수 판별을 시작한다.
			checkDecimal(checkNum);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		maxNumber = Integer.parseInt(br.readLine().trim());
		
		checkDecimal(0);
		System.out.println(sb);
		br.close();
	}
}