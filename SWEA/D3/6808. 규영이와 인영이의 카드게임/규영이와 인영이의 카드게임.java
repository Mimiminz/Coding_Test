import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 6808 규영이와 인영이의 카드게임
 * @author JOMINJU
 * 
 *	1. 인영이가 낼 수 있는 카드와 그 점수를 구한다
 *		1-1. boolean을 통해 18개의 배열을 구현한다.
 *		1-2. 한번 카드를 낼 때마다 규영이와 인영이가 내는 카드를 true로 바꾼다.
 *	2. 규영이가 내는 카드와 값을 비교하여 이기는 경우와 지는 경우의 수를 계산한다.
 *	3. 이기는 경우와 지는 경우를 공백으로 구분하여 출력한다.
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static boolean[] cards;
	public static int[] gyuyoung;
	public static int win;
	public static int lose;
	
	public static void dfs(int count, int in, int gyu) {
		if(count == 9) {
			if(gyu > in)
				win++;
			else if(gyu < in)
				lose++;
			
			return;
		}
		
		for(int idx = 0; idx < 18; idx++) {
			if(cards[idx] == false) {
				cards[idx] = true;
				if(idx+1 > gyuyoung[count])
					dfs(count+1, in+idx+1+gyuyoung[count], gyu);
				else
					dfs(count+1, in, gyu+idx+1+gyuyoung[count]);
				cards[idx] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			win = 0; lose = 0;
			cards = new boolean[18];
			gyuyoung = new int[9];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < 9; idx++) {
				gyuyoung[idx] = Integer.parseInt(st.nextToken());
				cards[gyuyoung[idx]-1] = true;
			}
			
			dfs(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb);
        br.close();
	}
}