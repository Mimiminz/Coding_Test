import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1759 암호 만들기
 * @author JOMINJU
 *
 * 1. 비밀번호의 개수와 암호로 사용가능한 알파벳을 입력받는다.
 * 2. 사전순으로 조합하기 위해 입력받은 알파벳을 정렬한다.
 * 2. 조합을 이용하여 가능한 암호를 전부 출력한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static char[] alphabet;
	public static boolean[] elementList;
	
	
	public static int passwordCount;
	public static int alphabetCount;
	
	public static void findPW(int count, int elementIdx) {
		if(count == passwordCount) {
			String password = "";
			int vowel = 0;
			for (int idx = 0; idx < alphabetCount; idx++) {
				if(elementList[idx]) {
					password += alphabet[idx];
					if(alphabet[idx] == 'a' || alphabet[idx] == 'e' || alphabet[idx] == 'i' || alphabet[idx] == 'o' || alphabet[idx] == 'u') {
						vowel++;
					}
				}
			}
			
			if(vowel >= 1 && vowel <= passwordCount-2) {
				sb.append(password).append("\n");
			}
			return;
		}
		
		if(elementIdx == alphabetCount)
			return;
		
		elementList[elementIdx] = true;
		findPW(count+1, elementIdx+1);
		
		elementList[elementIdx] = false;
		findPW(count, elementIdx+1);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		passwordCount = Integer.parseInt(st.nextToken());
		alphabetCount = Integer.parseInt(st.nextToken());
		elementList = new boolean[alphabetCount];
		alphabet = new char[alphabetCount];
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < alphabetCount; idx++) {
			alphabet[idx] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);
		
		findPW(0, 0);
		System.out.println(sb);
		br.close();
	}
}