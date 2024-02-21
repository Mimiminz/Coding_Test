import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ 13023 ABCDE
 * @author JOMINJU
 *
 *	1. dfs를 돌며 깊이의 숫자가 5가 되는 순간 dfs를 종료하고 1을 출력한다
 *	2. 입력받은 친구 관계를 ArrayList에 저장한다.
 *	3. 이후 0번부터 시작해 연결되는 친구 관계를 카운트 하고, 한번 확인한 친구는 방문 체크를 진행한다.
 *	4. 한번도 방문하지 않은 친구 관계를 돌며 카운트가 5가 되면 1을, 5가 되지 않으면 0을 출력한다.
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static ArrayList<Integer>[] relationship;
	public static boolean[] visited;
	
	public static int personNum;
	public static boolean result;
	
	public static void findFriends(int depth, int current) {
		if(depth == 5) {
			result = true;
			return;
		}
		
		visited[current] = true;
		for (int idx : relationship[current]) {
			if(!visited[idx]) {
				findFriends(depth+1, idx);
			}
		}
		visited[current] = false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		personNum = Integer.parseInt(st.nextToken());
		int relation = Integer.parseInt(st.nextToken());
		
		relationship = new ArrayList[personNum];
		visited = new boolean[personNum];
		
		for (int idx = 0; idx < personNum; idx++) {
			relationship[idx] = new ArrayList<Integer>();
		}

		for (int idx = 0; idx < relation; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());
			
			relationship[f1].add(f2);
			relationship[f2].add(f1);
		}
		
		for (int idx = 0; idx < personNum; idx++) {
			if(!result) {
				findFriends(1, idx);
			}
		}
		
		if(!result)
			sb.append(0);
        else
			sb.append(1);
		
		System.out.println(sb);
		br.close();
	}
}