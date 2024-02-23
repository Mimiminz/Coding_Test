import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 3124 최소 스패닝 트리
 * @author JOMINJU
 * 
 * 서로소 집합을 이용한 크루스칼 알고리즘
 * 
 *  1. 간선 리스트를 입력받는다.
 *  2. 오름차순으로 리스트를 정렬한다.
 *  3. 리스트에서 뽑아낸 가중치를 더하는 것을 반복한다.
 *  	3-1. 이때 같은 집합일 경우 사이클이 발생할 수 있기 때문에 같은 집합이면 더하지 않는다.
 *  4. 이때 뽑아낸 정점의 개수가 입력받은 정점의 개수가 동일해지면 리스트를 도는 것을 멈춘다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int vertexCount; 
	public static int edgeCount; 
	public static Edge[] edgeList; 
	public static int[] parents; 
	public static int[] ranks;
	
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		// 간선의 가중치가 낮은 순으로 정렬한다
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static boolean union(int element1, int element2) {
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 같은 집합에 포함되는 경우
		if (e1Parent == e2Parent) {
			return false;
		}
		
		// e1Parent의 랭크가 더 높을 경우
		if (ranks[e1Parent] > ranks[e2Parent]) {
			parents[e2Parent] = e1Parent;
			return true;
		}
		
		// e2Parent의 랭크가 더 높거나 같을 경우
		parents[e1Parent] = e2Parent;
		
		// 두 집합의 랭크가 같은 경우
		if (ranks[e1Parent] == ranks[e2Parent]) {
			ranks[e2Parent]++;
		}
		return true;
	}
	
	public static int find(int element) {
		if (element == parents[element]) {
			return element;
		}
		
		// 원소의 부모를 찾고 갱신하기
		return parents[element] = find(parents[element]); 
	}
	
	public static void make() {
		parents = new int[vertexCount + 1];
		ranks = new int[vertexCount + 1];
		
		for (int idx = 1; idx < vertexCount + 1; idx++) {
			parents[idx] = idx;
			ranks[idx] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			vertexCount = Integer.parseInt(st.nextToken());
			edgeCount = Integer.parseInt(st.nextToken());
			
			edgeList = new Edge[edgeCount];
			
			for (int idx = 0; idx < edgeCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				// 간선 리스트 생성
				edgeList[idx] = new Edge(from, to, weight);
			}
			
			// 오름차순으로 정렬
			Arrays.sort(edgeList);
			
			make();
			
			// 간선을 하나씩 꺼내서 신장 트리를 생성한다
			long weight = 0;
			int count = 0;
			
			for (Edge edge: edgeList) {
				// 이미 같은 집합이므로 간선을 추가하면 사이클이 발생하게 된다 
				if (!union(edge.from, edge.to)) {
					continue;
				}
				
				// 추가할 간선의 가중치를 더한다
				weight += edge.weight;
				
				// 최소 신장 트리 완성 시 종료
				if (++count == vertexCount - 1) {
					break;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(weight).append("\n");
		}
		System.out.println(sb);
	}
}