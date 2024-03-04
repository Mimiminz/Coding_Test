
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	/*
	 * 회문의 회문
	 * 
	 * 길이가 홀수인 문자열 S가 회문의 회문일 때 조건
	 * - S는 회문이다.
	 * - S의 처음 (N-1)/2 글자가 회문이다.
	 * - S의 마지막 (N-1)/2 글자가 회문이다.
	 * 
	 * 위 3조건을 모두 만족하는 경우에 YES 아닌 경우 NO
	 * 
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int testCase;
	
	public static boolean checkPalindrome(char[] charList) {
		
		char[] firstList = new char[charList.length / 2];
		char[] secondList = new char[charList.length / 2];
		
		for(int idx=0; idx<charList.length / 2 ; idx++) {
			
			// 중간을 기준으로 앞부분
			firstList[idx] = charList[idx];
			// 중간을 기준으로 뒷부분
			secondList[idx] = charList[(charList.length-1) - idx];
		}
		
		
		
		// 두 배열을 비교
		for(int idx=0; idx<charList.length / 2; idx++) {
			
			if(firstList[idx] != secondList[idx]) {
				return false;
			}
			
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=testCase; tc++) {
			
			String answer;
			
			char[] inputString = br.readLine().trim().toCharArray();
			
			char[] firstList = new char[inputString.length / 2];
			char[] secondList = new char[inputString.length / 2];
			
			for(int idx=0; idx<inputString.length / 2 ; idx++) {
				
				// 중간을 기준으로 앞부분
				firstList[idx] = inputString[idx];
				// 중간을 기준으로 뒷부분
				secondList[idx] = inputString[(inputString.length-1) - idx];
			}
			
			if(checkPalindrome(inputString) 
					&& checkPalindrome(firstList) 
					&& checkPalindrome(secondList)) {
				
				answer = "YES";
			} else {
				answer = "NO";
			}
				
			sb.append(String.format("#%d %s\n", tc, answer));
			
		}
		System.out.println(sb);
	}
}
