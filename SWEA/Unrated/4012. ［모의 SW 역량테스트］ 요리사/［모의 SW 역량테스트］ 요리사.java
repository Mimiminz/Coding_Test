import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 4012 요리사
 * @author SSAFY
 * 1. n/2개의 재료를 구하는 함수를 작성한다.
 * 2. 해당 재료를 돌면서 재료값의 차를 구한다.
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] ingredients;
	public static int minValue;
	public static int ELEMENT_COUNT;
	public static int SELECT_COUNT;
	
	public static boolean[] selectList;
	
	public static int taste() {
		int foodA = 0;
		int foodB = 0;
		for(int col = 0; col < ELEMENT_COUNT-1; col++) {
			for(int row = col+1; row < ELEMENT_COUNT; row++) {
				if(row == col)	continue;
				else if(selectList[col] && selectList[row])
					foodA += ingredients[col][row] + ingredients[row][col];
				else if(!selectList[col] && !selectList[row])
					foodB += ingredients[col][row] + ingredients[row][col];
				
			}
		}
		return Math.abs(foodA-foodB);
	}
	
	public static void combination(int selectIdx, int elementIdx) {
		if(selectIdx == SELECT_COUNT) {
			minValue = Math.min(minValue, taste());		
			return;
		}
		
		if(elementIdx == ELEMENT_COUNT)
			return;
		
		selectList[elementIdx] = true;
		combination(selectIdx+1, elementIdx+1);
		
		selectList[elementIdx] = false;
		combination(selectIdx, elementIdx+1);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			ELEMENT_COUNT = Integer.parseInt(br.readLine().trim());
			SELECT_COUNT = ELEMENT_COUNT/2;
			ingredients = new int[ELEMENT_COUNT][ELEMENT_COUNT];
			selectList = new boolean[ELEMENT_COUNT];
			minValue = Integer.MAX_VALUE;
			for(int col = 0; col < ELEMENT_COUNT; col++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int row = 0; row < ELEMENT_COUNT; row++) 
					ingredients[col][row] = Integer.parseInt(st.nextToken());
			}
			combination(0,0);
			sb.append("#").append(tc).append(" ").append(minValue).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}