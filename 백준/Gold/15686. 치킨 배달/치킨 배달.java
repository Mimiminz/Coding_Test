import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ 15686 치킨 배달
 * @author JOMINJU
 *
 * 1. 도시를 돌며 치킨집의 위치를 이차원 배열에 저장한다.
 * 1. 조합을 통해 M개의 치킨집을 뽑아낸다.
 * 2. 각 집에서 치킨집을 돌며 뽑은 M개의 치킨 집 중 가장 가까운 치킨집과의 거리를 계산한다.
 * 3. 이후 가장 적은 거리를 출력한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int size;
	public static int maxShop;
	public static int[][] village;
	public static int[] pick;
	public static ArrayList<int[]> shops;
	public static int result;
	
	public static void pickShop(int selectIdx, int elementIdx) {
		if(selectIdx == maxShop) {
			int distance = 0;
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if(village[row][col] == 1) {
						distance += findDistance(row, col);
					}
				}
			}	
			
			result = Math.min(result, distance);
			return;
		}
		
		if(elementIdx == shops.size()) {
			return;
		}
		
		pick[selectIdx] = elementIdx;
		pickShop(selectIdx+1, elementIdx+1);
		
		pick[selectIdx] = -1;
		pickShop(selectIdx, elementIdx+1);
	}
	
	public static int findDistance(int row, int col) {
		int min = Integer.MAX_VALUE;
		for (int idx = 0; idx < maxShop; idx++) {
			int shopRow = shops.get(pick[idx])[0];
			int shopCol = shops.get(pick[idx])[1];
			min = Math.min(min, Math.abs(row - shopRow) + Math.abs(col - shopCol));
		}
		
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		size = Integer.parseInt(st.nextToken());
		maxShop = Integer.parseInt(st.nextToken());
		village = new int[size][size];
		pick = new int[maxShop];
		result = Integer.MAX_VALUE;
		
		shops = new ArrayList<>();
		
		for (int row = 0; row < size; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < size; col++) {
				village[row][col] = Integer.parseInt(st.nextToken());
				// 치킨집 위치 저장하기
				if(village[row][col] == 2) {
					shops.add(new int[] {row, col});
				}
			}
		}	
		
		pickShop(0, 0);
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
}