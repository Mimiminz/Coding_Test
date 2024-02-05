import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[] scores;
	public static int[] kcals;
	public static boolean[] selectList;
	
	public static int ELEMENT_COUNT;
	public static int limitK;
	
	public static int maxScore;
	
	public static void combination(int kcalSum, int scoreSum, int selectIdx) {
		if(kcalSum > limitK)
			return;
		
		if(selectIdx == ELEMENT_COUNT) {
			if(kcalSum <= limitK) {
				maxScore = Math.max(maxScore, scoreSum);
			}
			return;
		}
		
		selectList[selectIdx] = true;
		//maxScore = Math.max(maxScore, scoreSum);
		combination(kcalSum + kcals[selectIdx], scoreSum + scores[selectIdx], selectIdx +1);
		
		selectList[selectIdx] = false;
		combination(kcalSum, scoreSum, selectIdx +1);

	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			maxScore = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine().trim());
			ELEMENT_COUNT = Integer.parseInt(st.nextToken());
			limitK = Integer.parseInt(st.nextToken());
			scores = new int[ELEMENT_COUNT];
			kcals = new int[ELEMENT_COUNT];
			selectList = new boolean[ELEMENT_COUNT];
			for(int idx = 0; idx < ELEMENT_COUNT; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				scores[idx] = Integer.parseInt(st.nextToken());
				kcals[idx] = Integer.parseInt(st.nextToken());
			}
			combination(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}