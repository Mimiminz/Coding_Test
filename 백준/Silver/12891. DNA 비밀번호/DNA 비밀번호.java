import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  BOJ 12891 DNA 비밀번호
 *  @author JOMIN
 *  
 *  1. 먼저 subSize 정도의 길이를 가진 dna배열의 dnaChar의 개수를 구해준다.
 *  2. 조건을 충족하는지 확인한다.
 *  3. 이후 그 다음의 문자열은 더하고, 앞의 문자열을 빼며 확인하는 것을 반복한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		int dnaSize = Integer.parseInt(st.nextToken());
		int subSize = Integer.parseInt(st.nextToken());
		String dna = br.readLine().trim();
		
		int result = 0;
		
		st = new StringTokenizer(br.readLine().trim());
		
		// A, C, G, T
		int[] essential = new int[4];
		int[] haveDna = new int[4];
		
		// 꼭 있어야 하는 dna의 개수 입력
		for(int idx = 0; idx < 4; idx++) {
			essential[idx] = Integer.parseInt(st.nextToken());
		}
		
		// subSize만큼의 해당하는 dna개수 입력
		for(int idx = 0; idx < subSize; idx++) {
			switch(dna.charAt(idx)) {
				case'A':
					haveDna[0]++;
					break;
				case'C':
					haveDna[1]++;
					break;
				case'G':
					haveDna[2]++;
					break;
				case'T':
					haveDna[3]++;
					break;
			}
		}
		
		for(int idx = subSize; idx < dnaSize; idx++) {
			if(haveDna[0] >= essential[0] && haveDna[1] >= essential[1] && haveDna[2] >= essential[2] && haveDna[3] >= essential[3]) {
				result++;
			}
			switch(dna.charAt(idx)) {
				case'A':
					haveDna[0]++;
					break;
				case'C':
					haveDna[1]++;
					break;
				case'G':
					haveDna[2]++;
					break;
				case'T':
					haveDna[3]++;
					break;
			}
			switch(dna.charAt(idx-subSize)) {
				case'A':
					haveDna[0]--;
					break;
				case'C':
					haveDna[1]--;
					break;
				case'G':
					haveDna[2]--;
					break;
				case'T':
					haveDna[3]--;
					break;
			}
		}
		
		if(haveDna[0] >= essential[0] && haveDna[1] >= essential[1] && haveDna[2] >= essential[2] && haveDna[3] >= essential[3]) {
			result++;
		}
		
		sb.append(result);
		System.out.println(sb);
		br.close();
		
	}
}