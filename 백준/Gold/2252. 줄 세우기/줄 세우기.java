import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ 2252 줄 세우기
 * @author JOMINJU
 *
 * 1. 노드를 이용하여 학생들의 앞에서부터 줄을 세운다.
 * 2. A번 학생의 다음 노드에 B번 학생을 연결한다.
 * 3. A번 학생의 앞에 설 학생이 주어지는 경우 A의 다음 노드를 해당 학생으로 변경한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		int studentNum = Integer.parseInt(st.nextToken());
		int count = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] studentNumber = new int[studentNum+1];
		ArrayList[] students = new ArrayList[studentNum + 2];
		for (int idx = 0; idx <= studentNum + 1; idx++) {
			students[idx] = new ArrayList<Integer>();
		}
		
		int studentA;
		int studentB;
		
		for (int idx = 0; idx < count; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			studentA = Integer.parseInt(st.nextToken());
			studentB = Integer.parseInt(st.nextToken());
			students[studentA].add(studentB);
			studentNumber[studentB]++;
		}
		
		for (int idx = 1; idx < studentNumber.length; idx++) {
			if(studentNumber[idx] == 0)
				queue.offer(idx);
		}
		
		while(!queue.isEmpty()) {
			int No = queue.poll();
			sb.append(No).append(" ");
			
			List<Integer> list = students[No];
			
			for (int idx = 0; idx < list.size(); idx++) {
				int temp = list.get(idx);
				studentNumber[temp]--;
				
				if(studentNumber[temp] == 0) {
					queue.offer(temp);
				}
			}
		}
		
		System.out.println(sb);
		br.close();
	}
}
