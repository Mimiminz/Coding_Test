import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1260 DFS와 BFS
 * @author JOMINJU
 * 
 * 1. 재귀를 이용해 dfs를 구성한다.
 * 2. 각 연결된 노드를 이차원 배열에 집어넣는다.
 * 3. 방문 여부를 나타내는 visited 배열을 선언한다.
 * 4. 만약 방문하지 않았다면 방문 표시를 해준 뒤 dfs를 선언한다.
 * 5. 큐를 이용해 bfs를 구성한다.
 * 6. 시작 노드에서 갈 수 있는 노드를 큐에 집어넣는다.
 * 7. 이후 방문한 노드는 방문 표시를 해준다.
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int nodeNum;
	public static int lineNum;
	public static int startNode;
	public static boolean[] visited;
	public static ArrayList<Integer>[] nodes;
	public static Queue<Integer> que;
	
	public static void dfs(int current) {
		sb.append(current).append(" ");
		for(int idx = 0; idx < nodes[current].size(); idx++) {
			if(visited[nodes[current].get(idx)] == false) {
				visited[nodes[current].get(idx)] = true;
				dfs(nodes[current].get(idx));
			}
		}
	}
	
	public static void bfs(int start) {
		que.offer(start);
		visited[start] = true;
		while(!que.isEmpty()) {
			int current = que.poll();
			sb.append(current).append(" ");
			for(int idx = 0; idx < nodes[current].size(); idx++) {
				if(visited[nodes[current].get(idx)] == false) {
					visited[nodes[current].get(idx)] = true;
					que.offer(nodes[current].get(idx));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		nodeNum = Integer.parseInt(st.nextToken());
		lineNum = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(st.nextToken());
		visited = new boolean[nodeNum+1];
		visited[0] = true;
		que = new LinkedList<Integer>();
		
		nodes = new ArrayList[nodeNum+1];
		for(int idx = 0; idx <= nodeNum; idx++) {
			nodes[idx] = new ArrayList<Integer>();
		}
		for(int idx = 0; idx < lineNum; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			nodes[first].add(second);
			nodes[second].add(first);
		}
		for(int idx = 0; idx <= nodeNum; idx++) {
			Collections.sort(nodes[idx]);
		}
		visited[startNode] = true;
		dfs(startNode);
		sb.append("\n");
		Arrays.fill(visited, false);
		bfs(startNode);
		System.out.println(sb);
		br.close();
	}
}
