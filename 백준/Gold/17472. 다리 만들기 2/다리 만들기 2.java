
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ 17472 다리 만들기2
 * @author JOMINJU
 *
 *  1. 각 섬을 번호로 표시한다.
 *  2. 각 섬에서 다른 점까지 연결할 수 없으면 0, 연결할 수 있으면 다리의 최소 길이를 입력한다.
 *  3. 최소 스패닝 트리를 이용해 최소로 연결할 수 있는 다리의 길이를 구한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int rowSize;
	public static int colSize;
	public static int[][] map;
	public static boolean[][] visited;
	public static int result;
	public static List<Node> nodeList;
	public static int[] resultList;
	
	public static int islandNum;
	
	public static int[] parents;
	public static int[] rankeList;
	
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int weight;
		
		Node(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	public static boolean union(int e1, int e2) {
		int e1Parent = findParent(e1);
		int e2Parent = findParent(e2);

		if(e1Parent == e2Parent) {
			return false;
		}
		
		
		if(rankeList[e1Parent] > rankeList[e2Parent]){
			parents[e2Parent] = e1Parent;
			return true;
		}
		
		parents[e1Parent] = e2Parent;
		
		if(rankeList[e1Parent] == rankeList[e2Parent]) {
			rankeList[e2Parent]++;
		}
		return true;
	}
	
	public static int findParent(int element) {
		if(parents[element] == element) {
			return element;
		}
		else {
			return parents[element] = findParent(parents[element]);
		}
	}
	
	public static void make() {
		parents = new int[islandNum];
		rankeList = new int[islandNum];
		for (int idx = 1; idx < islandNum; idx++) {
			parents[idx] = idx;
		}
	}
	
	public static void islandSetNumber(int row, int col, int value) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {row, col});
		map[row][col] = value;
		
		while(!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			for (int delta = 0; delta < 4; delta++) {
				int newRow = current[0] + dx[delta];
				int newCol = current[1] + dy[delta];
				
				if(newRow >= 0 && newCol >= 0 && newRow < rowSize && newCol < colSize && map[newRow][newCol] == 1) {
					map[newRow][newCol] = value;
					queue.add(new int[] {newRow, newCol});
				}	
			}
		}
	}
	
	
	public static void distance(int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {row, col});
		int value = map[row][col]-1;
		
		int[] distances = new int[islandNum];
		Arrays.fill(distances, 1000);
		
		while(!queue.isEmpty()) {
			
			int[] current = queue.poll();
			
			for (int delta = 0; delta < 4; delta++) {
				int newRow = current[0] + dx[delta];
				int newCol = current[1] + dy[delta];
				
				if(newRow >= 0 && newCol >= 0 && newRow < rowSize && newCol < colSize && !visited[newRow][newCol] && map[newRow][newCol] == value+1) {
					visited[newRow][newCol] = true;
					queue.add(new int[] {newRow, newCol});
				}	
			}
			
			for (int delta = 0; delta < 4; delta++) {
				int newRow = current[0];
				int newCol = current[1];
				int count = 0;
				
				while(true) {
					newRow += dx[delta];
					newCol += dy[delta];
					
					if(newRow < 0 || newCol < 0 || newRow >= rowSize || newCol >= colSize || map[newRow][newCol] == value+1) {
						break;
					}	
					
					if(map[newRow][newCol] != 0) {
						if(count >= 2 && count < distances[map[newRow][newCol]-1]) {
							distances[map[newRow][newCol]-1] = count;
						}
						break;
					}
					
					count++;
				}
			}
		}
		
		for (int idx = 1; idx < islandNum; idx++) {
			if(distances[idx] != 1000) {
				resultList[idx] = Math.min(resultList[idx], distances[idx]);
				nodeList.add(new Node(value, idx, distances[idx]));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		islandNum = 1;
		result = 0;
		nodeList = new ArrayList<>();
		
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if(map[row][col] == 1) {
					islandNum++;
					islandSetNumber(row, col, islandNum);
				}
			}
		}
		
		int c1 = 2;
		resultList = new int[islandNum];
		Arrays.fill(resultList, 1000);
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if(map[row][col] == c1) {
					c1++;
					distance(row, col);
				}
			}
		}
		
		make();
		
		boolean[] connectCheck = new boolean[islandNum];
		int bridgeCount = 0;
		
		Collections.sort(nodeList);
		
		for(Node n : nodeList) {
			if(findParent(n.from) != findParent(n.to)) {
				connectCheck[n.from] = true;
				connectCheck[n.to] = true;
				
				union(n.from, n.to);
				
				result += n.weight;
				bridgeCount++;
			}
		}
		
		for (int i = 1; i < islandNum; i++) {
			if(!connectCheck[i]) {
				result = -2;
			}
		}
		
		if (bridgeCount != islandNum-2) {
			result = -1;
		}

		sb.append(result);
		System.out.println(sb);
		
		return;
	}
}
