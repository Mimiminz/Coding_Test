import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 5607 조합 (페르마의 소정리 이용)
 * @author JOMINJU
 * 
 *  페르마의 소정리를 이용한 값 구하기
 *  nCr의 경우 (N!)/R!*(N-R)!이다
 *  즉 nCr = N! * (R! * (N_R)!)-¹
 *  이걸 페르마의 소정리에 넣어 잘 비벼보면 N!((N-R)!R!)k-2제곱은 nCr(mod k)다.
 *  (* 이때 k는 나눌 값. 여기서는 1234567891을 의미)
 *  
 *  결론적으로 nCr을 k로 나눈 나머지는 왼쪽 저 값을 k로 나눈 값과 동일하다는 거...
 *  
 *  
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static final int modValue = 1234567891;
	
	public static long pow(long c, long n) {
		if(n == 1) {
			return c % modValue;
		}
		
		long res = pow(c, n/2) % modValue;
		
		if(n % 2 == 0) {
			return res * res % modValue;
		}else {
			return (res * res) % modValue * c % modValue;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long rfacto = 1;
			for (int idx = 2; idx <= R; idx++) {
				rfacto = rfacto * idx % modValue;
			}
			
			long nrfacto = rfacto;
			for (int idx = R+1; idx <= N-R; idx++) {
				nrfacto = nrfacto * idx % modValue;
			}
			
			long nfacto = nrfacto;
			for (int idx = N-R+1; idx <= N; idx++) {
				nfacto = nfacto * idx % modValue;
			}
			
			
			long baseValue = nrfacto * rfacto % modValue;
			int k = modValue-2;
			long deforeDiv = pow(baseValue, k) % modValue;
			
			sb.append("#").append(tc).append(" ").append(deforeDiv * nfacto % modValue).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
