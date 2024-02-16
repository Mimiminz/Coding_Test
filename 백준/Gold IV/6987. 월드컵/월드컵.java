import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 6987 월드컵
 * @author JOMINJU
 *
 * 1. 다른 국가들과 1번씩 총 5번의 경기 -> 즉 최종 숫자의 합으로는 불가능
 * 		1-1. 할 수 있는 경기 횟수는 총 15번
 * 2. 모든 경기를 하면 경우의 수가 세가지 생긴다.
 * 		2-1. 왼쪽이 승, 오른쪽이 패
 * 		2-2. 무승부
 * 		2-3. 왼쪽이 패, 오른쪽이 승
 * 3. 모든 경우의 수를 돌면서 기자가 보낸 결과와 일치하지 않을 경우 0을 리턴한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] report = new int[6][3];
	public static int isPossible;
	
	// 기자가 보낸 결과와 일치하는지 확인하는 함수
	public static int isPossible() {
		//System.out.println("--------------");
		for(int nation = 0; nation < 6; nation++) {
			for(int check = 0; check < 3; check++) {
				//System.out.printf(report[nation][check] + " ");
				if(report[nation][check] != 0)
					return 0;
			}
			//System.out.println();
		}
		return 1;
	}
	
	public static void checkResult(int left, int right) {
		if(isPossible == 1)
			return;
		
		if(left == 6) {
			if(isPossible() == 1) {
				isPossible = 1;
			}
			return;
		}
		
		if(right >= 6) {
			checkResult(left+1, left+2);
			return;
		}
		
		// 왼쪽이 이긴경우
		if(report[left][0] > 0 && report[right][2] > 0) {
			report[left][0]--;
			report[right][2]--;
			
			checkResult(left, right+1);
			// 빠져나온 경우 후처리
			report[left][0]++;
			report[right][2]++;
		}
		
		// 비긴 경우
		if(report[left][1] > 0 && report[right][1] > 0) {
			report[left][1]--;
			report[right][1]--;
			
			checkResult(left, right+1);
			
			report[left][1]++;
			report[right][1]++;
		}
		
		// 오른쪽이 이긴경우
		if(report[left][2] > 0 && report[right][0] > 0) {
			report[left][2]--;
			report[right][0]--;
			
			checkResult(left, right+1);
			
			report[left][2]++;
			report[right][0]++;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		for(int tc = 0; tc < 4; tc++) {
			isPossible = 0;
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < 6; idx++) {
				report[idx][0] = Integer.parseInt(st.nextToken());
				report[idx][1] = Integer.parseInt(st.nextToken());
				report[idx][2] = Integer.parseInt(st.nextToken());
			}
			checkResult(0, 1);
			sb.append(isPossible).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
}