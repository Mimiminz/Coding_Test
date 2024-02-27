import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1753 최단경로
 * @author JOMINJU
 *
 * 1. 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static Edge[] edgeList;
	public static int[] weightList;
	public static boolean[] visited;
	
	public static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		Edge next;
		
		public Edge(int to, int weight, Edge next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		};
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		int nodeCount = Integer.parseInt(st.nextToken());
		int edgeCount = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(br.readLine().trim());
		
		edgeList = new Edge[nodeCount+1];
		weightList = new int[nodeCount+1];
		visited = new boolean[nodeCount+1];
		
		// 모든 가중치는 최대로 설정
		Arrays.fill(weightList, INF);
		// 시작점의 경우만 0으로 초기화
		weightList[startNode] = 0;
		
		// 시작점과 끝, 가중치 넣기
		for (int idx = 0; idx < edgeCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[from] = new Edge(to, weight, edgeList[from]);
		}
		
		int min = 0;
		int nearNode = 0;
		
		for (int idx = 1; idx <= nodeCount; idx++) {
			min = INF;
			nearNode = -1;
			
			// 아직 방문하지 않은 정점 중 출발지에서 가장 가까운 정점 선택
			for (int near = 1; near <= nodeCount; near++) {
				if(!visited[near] && min > weightList[near]) {
					min = weightList[near];
					nearNode = near;
				}
			}
			
			// 정점을 고를 수 없었다면 종료
			if(nearNode == -1)
				break;
			
			visited[nearNode] = true;
			
			for(Edge edge = edgeList[nearNode]; edge != null; edge = edge.next) {
				if(weightList[edge.to] > min + edge.weight) {
					weightList[edge.to] = min + edge.weight;
				}
			}
		}
		
		for (int idx = 1; idx <= nodeCount; idx++) {
			sb.append(weightList[idx] == INF ? "INF" : weightList[idx]).append("\n");	
		}
		
		System.out.println(sb);
		br.close();
	}
}