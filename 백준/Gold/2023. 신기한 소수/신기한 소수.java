import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
 *  BOJ 신기한 소수
 *  @author JOMINJU
 *  
 *  1. 해당 숫자가 소수인지 판별하는 함수를 작성한다.
 *  2. 소수가 아니라면 함수를 다음으로 넘긴다
 *  3. 소수라면 다음수를 넣고 다음 자리 소수 판별을 시작한다.
 *  4. N번째 자리까지 도달한다면, 값을 리턴하고 다음 수를 확인한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static int maxNumber;
	
	public static boolean checkDecimal(int num) {
		
		if(num == 1)
			return false;
	    
	    // n의 제곱근까지 나눈다.
	    for(int idx = 2; idx <= Math.sqrt(num); idx++) {   
	        // 소수라면 뒤에 오는 코드를 스킵한다.
	    	if(num % idx == 0) {
				return false;
			}
		}
	    
	    return true;
	}
	
	public static void makeNum(int current) {
		
		// 판별할 수 만들기
		for(int idx = 1; idx < 10; idx++) {
			// 한자리 늘리기
			int checkNum = current*10 + idx;
			
			boolean isPrime = checkDecimal(checkNum);
			
			// 값이 소수가 아니라면 넘어가기
			if(isPrime == false)
				continue;
			
			// 4. N번째 자리까지 도달한다면, 값을 리턴하고 다음 수를 확인한다.
			if(Integer.toString(checkNum).length() == maxNumber) {
				sb.append(checkNum).append("\n");
				continue;
			}
			// 3. 소수라면 다음수를 넣고 다음 자리 소수 판별을 시작한다.
			makeNum(checkNum);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		maxNumber = Integer.parseInt(br.readLine().trim());
		
		makeNum(0);
		System.out.println(sb);
		br.close();
	}
}