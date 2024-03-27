
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 4485 녹색 옷 입은 애가 젤다지?
 * @author JOMINJU
 *
 *	
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int size;
	public static int[][] cave;
	public static int rupee;
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	static class Node implements Comparable<Node>{
		
		int row;
		int col;
		int cost;
		
		Node(int row, int col, int cost){
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	
	public static void passCave() {
		Queue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0, 0, cave[0][0]));
		int[][] move = new int[size][size];
		move[0][0] = cave[0][0];
		
		for (int idx = 0; idx < size; idx++) {
			Arrays.fill(move[idx], Integer.MAX_VALUE);
		}
		
		while(!queue.isEmpty()) {
			
			Node cur = queue.poll();
			int curRow = cur.row;
			int curCol = cur.col;
			int cost = cur.cost;
			
			if(curRow == size-1 && curCol == size-1) {
				rupee = Math.min(rupee, cost);
				return;
			}
			
			for (int direct = 0; direct < 4; direct++) {
				int row = curRow + dx[direct];
				int col = curCol + dy[direct];
				
				if(row < 0 || col < 0 || row >= size || col >= size) {
					continue;
				}
				
				
				if(cost + cave[row][col] < move[row][col]) {
					move[row][col] = cost + cave[row][col];
					queue.add(new Node(row, col, move[row][col]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int count = 1;
		
		while(true) {
			size = Integer.parseInt(br.readLine().trim());
			if(size == 0) {
				System.out.println(sb);
				br.close();
				return;
			}
			
			rupee = Integer.MAX_VALUE;
			cave = new int[size][size];
			
			for (int row = 0; row < size; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < size; col++) {
					cave[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			passCave();
			
			sb.append("Problem ").append(count++).append(": ").append(rupee).append("\n");
		}
	}
}
