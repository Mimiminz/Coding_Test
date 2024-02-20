import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * BOJ 2636 치즈
 * @author JOMINJU
 *
 * 1. 치즈가 없는 곳은 0, 치즈가 있는 곳은 1의 값을 넣는다.
 * 2. bfs를 톨며 값이 0이고 방문한 적 없는 곳에 위치에서 상하좌우에 있는 치즈의 좌표를 넣는다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static int[][] cheeses;
	public static boolean[][] visited;
	
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0};
	
	public static int cheese;

	
	public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for(int idx = 0; idx < 4; idx++) {
                int drow = current[0] + dx[idx];
                int dcol = current[1] + dy[idx];
                
                if(drow >= 0 && dcol >= 0 && drow < rowSize && dcol < colSize 
                		&& visited[drow][dcol] == false) {
                    visited[drow][dcol] = true;
                    if(cheeses[drow][dcol] == 0) {
                        queue.offer(new int[] {drow, dcol});
                    } else {
                        cheese--;
                        cheeses[drow][dcol] = 0;
                    }
                }
            }
        }
	    
	}
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		cheeses = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		cheese = 0;

		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				cheeses[row][col] = Integer.parseInt(st.nextToken());
				if(cheeses[row][col] == 1) {
					cheese++;
				}
			}
		}
		
		int cheeseCount = 0;
        int time = 0;
        while(cheese != 0) {
            cheeseCount = cheese;
            time++;
            visited = new boolean[rowSize][colSize];
            bfs();
        }
		sb.append(time).append("\n").append(cheeseCount);
		System.out.println(sb);
	}
}
