import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 10971 외판원 순회2
 * @author JOMINJU
 *
 * 1. 1번 도시에서 시작해서 1번 도시로 돌아오는 것을 가정
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static final int INF = 987654321;
	public static int[][] country;
	public static int[][] checkCountry;
	public static int complete;
	public static int countryCount;
	
	public static int visitCountry(int current, int visited) {
		if(visited == complete) {
			if(country[current][0] == 0) {
				return INF;
			}else {
				return country[current][0];
			}
		}
		
		if(checkCountry[current][visited] != -1) {
			return checkCountry[current][visited];
		}
		
		checkCountry[current][visited] = INF;
		
		for (int idx = 0; idx < countryCount; idx++) {
			int next = visited | (1<<idx);
			
			if(country[current][idx] == 0 || (visited & (1<<idx)) != 0) {
				continue;
			}
			
			checkCountry[current][visited] = Math.min(checkCountry[current][visited], visitCountry(idx, next) + country[current][idx]);
		}
		return checkCountry[current][visited];
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		countryCount = Integer.parseInt(br.readLine().trim());
		country = new int[countryCount][countryCount];
		complete = (1<<countryCount) - 1;
		checkCountry = new int[countryCount][complete];
		
		// 도시 비용 저장
		for (int row = 0; row < countryCount; row++) {
			st = new StringTokenizer(br.readLine().trim());
			Arrays.fill(checkCountry[row], -1);
			for (int col = 0; col < countryCount; col++) {
				country[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		sb.append(visitCountry(0, 1));
		System.out.println(sb);
		br.close();
	}
}
