import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 17471 게리멘더링
 * @author JOMINJU
 * 
 * 부분조합 사용
 *
 * 1. 각 구역의 인구수를 입력받는다.
 * 2. 각 구역의 인접한 구역의 개수와 구역 번호를 입력받는다.
 * 3. 1번을 시작 구역으로 잡고 나머지 구역의 인구수를 센다.
 * 4. 1번일 경우의 인구수와 방문하지 않은 하나의 구역에서 시작한 인구수의 차이를 저장한다.
 * 		4-1. 이때 두 경우의 인구수의 합이 최종 인구수와 같지 않을 경우에는 그냥 종료한다.
 * 5. 인접한 구역을 하나씩 늘리며 인구수의 차이가 가장 작은 경우의 수를 구한다.
 * 6. 이때 1번을 포함한 구역과 나머지 인구수의 크기가 반대로(큰쪽이 작아지면) 되면 종료하고 현재의 최소 인구수를 반환한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int areaCount;
	public static int[] areaPerson;
	public static boolean[] visited;
	public static List<ArrayList<Integer>> graph;
	
	public static int minValue;
	
	public static void divide(int selectIdx) {
		if(selectIdx == areaCount) {
			List<Integer> aList = new ArrayList<>();
			List<Integer> bList = new ArrayList<>();
			for (int idx = 0; idx < areaCount; idx++) {
				if (visited[idx])
					aList.add(idx);
				else
					bList.add(idx);
			}
			if (aList.size() == 0 || bList.size() == 0) // 한 지역에 몰빵 X
				return;
			
			if (check(aList) && check(bList)) { // 두 구역이 각각 연결되었는지 확인
				getPeopleDiff(); // 인구차 구하기
			}
			return;
		}
		
		visited[selectIdx] = true;
		divide(selectIdx + 1);
		
		visited[selectIdx] = false;
		divide(selectIdx + 1);
	}
	
	private static boolean check(List<Integer> list) {

		Queue<Integer> queue = new LinkedList<>();
		boolean[] check = new boolean[areaCount];
		check[list.get(0)] = true;
		queue.offer(list.get(0));
		
		int count = 1; // 방문한 지역 개수
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int y = graph.get(cur).get(i);
				if(list.contains(y) && !check[y]) { // 선거구에 해당하고, 아직 미방문
					queue.offer(y);
					check[y] = true;
					count ++;
				}
			}
		}
		if(count==list.size()) // 선거구에 해당하는 지역수와 방문한 지역수가 같은 경우
			return true;
		else
			return false;
	}
	
	public static void getPeopleDiff() { // 3. 선거구의 인구 차 구하기
		// a구역 사람수
		int pA = 0, pB = 0;
		for (int idx = 0; idx < areaCount; idx++) {
			if (visited[idx])
				pA += areaPerson[idx];
			else
				pB += areaPerson[idx];
		}
		int diff = Math.abs(pA - pB);
		minValue = Math.min(minValue, diff);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		areaCount = Integer.parseInt(br.readLine().trim());
		areaPerson = new int[areaCount];
		minValue = Integer.MAX_VALUE;
		visited = new boolean[areaCount];
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < areaCount; idx++) {
			areaPerson[idx] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<>();
		for (int idx = 0; idx <= areaCount; idx++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int idx = 0; idx < areaCount; idx++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); // 인접 구역 수
			for (int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(st.nextToken());
				graph.get(idx).add(num - 1);
			}
		}
		
		divide(0);
		
		if(minValue == Integer.MAX_VALUE)
			minValue = -1;
		sb.append(minValue);
		System.out.println(sb);
		br.close();
	}
}