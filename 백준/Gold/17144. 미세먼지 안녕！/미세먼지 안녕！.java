
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ 17144 미세먼지 안녕!
 * @author JOMINJU
 *
 * 첫번째로 입력받은 -1의 위쪽은 시계반대 방향, 두번째 -1를 기준으로 아래는 시계방향으로 돌아간다.
 * 
 * 1. 리스트에 미세먼지의 위치와 미세먼지의 양을 입력받는다.
 * 2. 모든 미세먼지를 돌며 맵에 미세먼지가 없고, 공청기가 없는 부분으로만 먼저 확산시킨다.
 * 		2-1. 이때 기존 미세먼지는 리스트에서 제거하고(사방이 막히게 되어 확산 불가) 새로운 먼지를 리스트에 넣는다.
 * 		2-2. 기존의 미세먼지가 있는 곳으로는 확산이 되지 않고 비어있던 곳에는 여러곳에서 확산이 가능
 * 		2-3. 확산을 할 미세먼지의 위치는 방문처리를 해서 그곳으로 방문하지 않게 하자
 * 3. 공기청정기를 작동시킨다.
 * 4. 입력받은 공기청정기의 위치에 따라 미세먼지 값이 시계방향, 반대방향으로 이동한다.
 * 		4-1. 이때 공기청정기의 위, 공기청정기의 아래에서 각 도는 방향 반대로 확인하면 될듯.
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static int time;
	
	public static int upAirLocation;
	public static int downAirLocation;
	
	public static int[][] map;
	public static Queue<int[]> dusts = new LinkedList<>();
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	public static void setDust() {
		dusts.clear();
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if(map[row][col] > 0) {
					dusts.offer(new int[] {row, col, map[row][col]});
				}
			}
		}
	}
	
	public static void diffusionDust() {
		while(!dusts.isEmpty()) {
			int[] current = dusts.poll();
			int value = current[2]/5;
			int count = 0;
			for (int delta = 0; delta < 4; delta++) {
				int row = current[0] + dx[delta];
				int col = current[1] + dy[delta];
				
				if(row < 0 || col < 0 || row >= rowSize || col >= colSize || map[row][col] == -1) {
					continue;
				}
				
				map[row][col] += value;
				count++;
			}
			
			map[current[0]][current[1]] -= value*count;
		}
	}
	
	public static void activeAirCleaner() {
		int row = upAirLocation-1;
		int col = 0;
		int direct = 0;
		while(true) {
			switch(direct) {
				case 0:
					map[row][col] = map[row-1][col];					
					row--;
					break;
				case 1:
					map[row][col] = map[row][col+1];
					col++;
					break;
				case 2:
					map[row][col] = map[row+1][col];
					row++;
					break;
				case 3:
					map[row][col] = map[row][col-1];
					col--;
					break;
			}
			map[row][col] = 0;
			if(direct == 0 && row == 0) {
				direct++;
			}else if(direct == 1 && col == colSize-1) {
				direct++;
			}else if(direct == 2 && row == upAirLocation) {
				direct++;
			}else if(direct == 3 && map[row][col-1] == -1) {
				break;
			}
		}
		
		row = downAirLocation+1;
		col = 0;
		direct = 0;
		
		while(true) {
			switch(direct) {
				case 0:
					map[row][col] = map[row+1][col];					
					row++;
					break;
				case 1:
					map[row][col] = map[row][col+1];
					col++;
					break;
				case 2:
					map[row][col] = map[row-1][col];
					row--;
					break;
				case 3:
					map[row][col] = map[row][col-1];
					col--;
					break;
			}
			map[row][col] = 0;
			if(direct == 0 && row == rowSize-1) {
				direct++;
			}else if(direct == 1 && col == colSize-1) {
				direct++;
			}else if(direct == 2 && row == downAirLocation) {
				direct++;
			}else if(direct == 3 && map[row][col-1] == -1) {
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];
		upAirLocation = 0;
		downAirLocation = 0;
		
		
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == -1) {
					if(upAirLocation == 0) {
						upAirLocation = row;
					}else {
						downAirLocation = row;
					}
				}
			}
		}
		
		for (int current = 0; current < time; current++) {
			setDust();		
			diffusionDust();
			activeAirCleaner();
		}
		
		int sumDust = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if(map[row][col] > 0) {
					sumDust += map[row][col];
				}
			}
		}
		sb.append(sumDust);

		System.out.println(sb);
		
		return;
	}
}
