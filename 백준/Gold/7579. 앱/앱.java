import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 7579 앱
 * @author JOMINJU
 *
 *	knapsack 문제인제 이제 최대치가 아니라 최소치를 구해야 하는 문제
 *
 *	P[i, w] i개의 앱이 있고, 필요한 메모리가 w일때 구할 수 있는 최소 비활성화 비용
 *
 * 1. 각 앱에 따라 드는 메모리 양에 따른 비용을 저장한다.
 * 2. 만약 앱이 1개일 경우에는 해당 앱의 메모리가 필요 메모리를 넘어가는 지 확인하고, 넘어갈 경우 비용을 비교해서 저장한다.
 * 3. 앱이 여러개일 경우에는 필요 메모리 양을 넘어갈 경우와 넘어가지 않을 경우를 비교해서 저장한다.
 * 4. 만약 메모리 양이 필요 메모리 양을 넘어갈 경우 해당 비용과 현재 저장된 비용 중 적은 값을 저장한다.
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	public static StringTokenizer st1;
	public static StringTokenizer st2;
	
	public static int appNum;
	public static int needByte;
	public static int[] apps;
	public static int[] moneys;
	
	public static int[][] values;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		appNum = Integer.parseInt(st.nextToken());
		needByte = Integer.parseInt(st.nextToken());
		
		apps = new int[appNum];
		moneys = new int[appNum];
		
		int total = 0;
		int result = Integer.MAX_VALUE;

		st1 = new StringTokenizer(br.readLine().trim());
		st2 = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < appNum; idx++) {
			apps[idx] = Integer.parseInt(st1.nextToken());
			moneys[idx] = Integer.parseInt(st2.nextToken());
			total += moneys[idx];
		}
		
		values = new int[appNum][total+1];
		
		for (int idx = 0; idx < appNum; idx++) {
			for (int moneyIdx = 0; moneyIdx <= total; moneyIdx++) {
				
				if(idx == 0) {
					if(moneyIdx >= moneys[idx]) {
						values[idx][moneyIdx] = apps[idx];
					}
				}else {
					// 메모리 바이트가 필요 바이트 값보다 클 경우
					if(moneyIdx >= moneys[idx]) {
						values[idx][moneyIdx] = Math.max(values[idx-1][moneyIdx-moneys[idx]]+apps[idx], values[idx-1][moneyIdx]);
					}else {
						values[idx][moneyIdx] = values[idx-1][moneyIdx];	
					}
				}
				
				
				if(values[idx][moneyIdx] >= needByte) {
					result = Math.min(result, moneyIdx);
				}
			}
		}
		
		System.out.println(result);
		br.close();
	}
}
