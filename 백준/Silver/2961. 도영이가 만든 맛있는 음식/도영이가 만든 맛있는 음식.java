import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 	BOJ 2961 도영이가 만든 맛있는 음식
 * 	@author JOMINJU
 * 
 * 	신맛 = 사용한 재료의 신 맛의 곱
 * 	쓴맛 = 사용한 재료의 쓴 맛의 합
 * 
 * 	신맛과 쓴맛의 제일 작은 차이. 재료는 적어도 하나
 * 
 * 	1. powerset을 이용하여 신맛과 쓴맛 각각에서 나올 수 있는 맛의 점수를 구해 리스트에 넣는다.
 * 	2. 이중 포문을 통해 쓴맛과 신맛의 가장 작은 값 차이를 구한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	// 재료, 각 재료의 신맛과 쓴맛
	public static int ingredient;
	public static int[] sours;
	public static int[] bitters;
	
	public static int result;
	
	public static boolean[] elementCheckList;
	
	// 1. powerset을 이용하여 신맛과 쓴맛 각각에서 나올 수 있는 맛의 점수를 구해 리스트에 넣는다.
	public static void powerSet(int selectIdx) {
		// 기저 조건
		if(selectIdx == ingredient) {
			int sour = 1;
			int bitter = 0;
			for(int idx = 0; idx < ingredient; idx++) {
				if(elementCheckList[idx]) {
					sour *= sours[idx];
					bitter += bitters[idx];
				}
			}
			if(bitter != 0)
				result = Math.min(result, Math.abs(sour - bitter));
			return;
		}
		
		elementCheckList[selectIdx] = true;
		powerSet(selectIdx+1);
		
		elementCheckList[selectIdx] = false;
		powerSet(selectIdx+1);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		ingredient = Integer.parseInt(br.readLine().trim());
		
		sours = new int[ingredient];
		bitters = new int[ingredient];
		elementCheckList = new boolean[ingredient];
		result = Integer.MAX_VALUE;
		
		for(int ig = 0; ig < ingredient; ig++) {
			st = new StringTokenizer(br.readLine().trim());
			sours[ig] = Integer.parseInt(st.nextToken());
			bitters[ig] = Integer.parseInt(st.nextToken());
		}
		
		// 신맛, 쓴맛 리스트 구하기
		powerSet(0);
		
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
}