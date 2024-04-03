import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * SWEA 4013 특이한 자석
 * @author JOMINJU
 * 
 * # N극이 0, S극이 1
 * # 화살표 위치의 날부터 시계방향으로 주어짐
 * 
 * 1. 자석의 정보를 입력받는다.
 * 2. 1번 4번의 날은 각각 2번, 3번만 확인한다.
 * 3. 2번, 3번 자석이 움직이는 경우 각각 양 옆이 돌았는지 아닌지 확인해야한다.
 * 		3-1. 자석이 시계방향으로 회전하는 경우 +1, 반시계방향으로 회전하는 경우 -1 값을 더한다.
 * 2. 만약 자석이 시계방향으로 회전하는 경우 
 * 		2-1. 1번의 날이 움직이는 경우 2번과 일치하는지 아닌지만 확인한다.
 * 		2-2. 만약 2번의 날이 움직이는 경우 
 * 
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;

	public static int rotationCount;
	public static int[][] magnet;
	public static int[][] rotation;
	public static int[] result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			rotationCount = Integer.parseInt(br.readLine().trim());
			magnet = new int[5][8];
			rotation = new int[rotationCount][2];
			result = new int[5];
			for (int idx = 1; idx <= 4; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int no = 0; no < 8; no++) {					
					magnet[idx][no] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int cnt = 0; cnt < rotationCount; cnt++) {
				st = new StringTokenizer(br.readLine().trim());
				rotation[cnt][0] = Integer.parseInt(st.nextToken());
				rotation[cnt][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int rotate = 0; rotate < rotationCount; rotate++) {
				int direct = rotation[rotate][1];
				int[] basic = result.clone();
				result[rotation[rotate][0]] += direct;
				for (int idx = rotation[rotate][0]; idx < 4; idx++) {
					// 다음 날이랑 자성이 달라 돌아가는 경우
					if(magnet[idx][(10-basic[idx])%8] != magnet[idx+1][(14-basic[idx+1])%8]) {
						result[idx+1] -= direct;
						direct *= -1;
					}// 자성이 달라 돌아가지 않는 경우
					else {
						break;
					}
				}
				
				direct = rotation[rotate][1];
				for (int idx = rotation[rotate][0]; idx > 1; idx--) {
					// 다음 날이랑 자성이 달라 돌아가는 경우
					if(magnet[idx][(14-basic[idx])%8] != magnet[idx-1][(10-basic[idx-1])%8]) {
						result[idx-1] -= direct;
						direct *= -1;
					}// 자성이 달라 돌아가지 않는 경우
					else {
						break;
					}
				}
			}
			
			int score = 0;
			if(magnet[1][(8-result[1])%8] == 1) {
				score += 1;
			}
			if(magnet[2][(8-result[2])%8] == 1) {
				score += 2;
			}
			if(magnet[3][(8-result[3])%8] == 1) {
				score += 4;
			}if(magnet[4][(8-result[4])%8] == 1) {
				score += 8;
			}
			
			sb.append("#").append(tc).append(" ").append(score).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
