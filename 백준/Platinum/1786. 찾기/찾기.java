
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * BOJ 1786 찾기
 * @author JOMINJU
 *
 * 1. 주어진 pattern 스트링에서 접미사와 접두사가 같은 최대 길이를 prefixTable에 저장한다.
 * 2. 기존에 비교해야 할 스트링과 패턴을 비교해서 만약 문자열이 일치하지 않을 경우 predixTable에 저장된 값으로 이동해 비교를 재시작한다.
 * 3. 문자열이 일치할 경우 기존 문자열에서 일치하는 문자열의 시작 위치를 반환한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int patternLength;
	public static String basic;
	public static String pattern;
	public static int[] prefixTable;
	public static List<Integer> matching;
	
	// prefixTable에 값 넣기
	public static void makeSuffix() {
		int prefixIdx = 0;
		
		for (int surfixIdx = 1; surfixIdx < patternLength; surfixIdx++) {
			while(prefixIdx > 0 && pattern.charAt(surfixIdx) != pattern.charAt(prefixIdx)) {
				prefixIdx = prefixTable[prefixIdx-1];
			}
			
			if(pattern.charAt(prefixIdx) == pattern.charAt(surfixIdx)) {
				prefixIdx++;
				prefixTable[surfixIdx] = prefixIdx;
			}
		}
	}
	
	public static void solve() {
		int prefixIdx = 0;
		
		for (int surfixIdx = 0; surfixIdx < basic.length(); surfixIdx++) {
			while(prefixIdx > 0 && basic.charAt(surfixIdx) != pattern.charAt(prefixIdx)) {
				prefixIdx = prefixTable[prefixIdx-1];
			}
			
			if(basic.charAt(surfixIdx) == pattern.charAt(prefixIdx)) {
				if(prefixIdx == patternLength-1) {
					matching.add(surfixIdx-prefixIdx+1);
					prefixIdx = prefixTable[prefixIdx];
				}else {
					prefixIdx++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		basic = br.readLine();
		pattern = br.readLine();
		matching = new ArrayList<>();
		patternLength = pattern.length();
		prefixTable = new int[patternLength];

		makeSuffix();
		
		solve();
		
		sb.append(matching.size()).append("\n");
		for(int value: matching) {
			sb.append(value).append("\n");
		}
		
		System.out.println(sb);
		
		return;
	}
}
