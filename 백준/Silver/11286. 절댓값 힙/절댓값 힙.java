import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * SWEA 11286 절대값 힙
 * @author JOMINJU
 *
 *	1. 가장 작은 값부터 출력해야 하기 때문에 최소힙을 구현한다.
 *	2. 두개중 비교해서 절대값이 작은 값부터 출력하도록 하기 위해 Math.abs를 통해 절대값으로 바꾼 값을 출력한다.
 *	3. 만약 절대값이 같은 경우, 값이 작은 것을 출력하기 위해 같을 경우는 절대값을 씌우지 않고 출력한다.
 */

public class Main{
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        // 1. 가장 작은 값부터 출력해야 하기 때문에 최소힙을 구현한다.
		@Override
        public int compare(Integer o1, Integer o2) {
        	// 2. 두개중 비교해서 절대값이 작은 값부터 출력하도록 하기 위해 Math.abs를 통해 절대값으로 바꾼 값을 출력한다.
        	if(Math.abs(o1) != Math.abs(o2))
        		return Integer.compare(Math.abs(o1),Math.abs(o2));
        	// 3. 만약 절대값이 같은 경우, 값이 작은 것을 출력하기 위해 같을 경우는 절대값을 씌우지 않고 출력한다.
        	return Integer.compare(o1,o2);
        }
    });

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int number = Integer.parseInt(br.readLine().trim());
		for(int idx = 0; idx < number; idx++) {
			int x = Integer.parseInt(br.readLine().trim());
			if(x == 0) {
				sb.append(minHeap.isEmpty() ? 0 : minHeap.remove()).append("\n");
			}else {
				minHeap.add(x);
			}
		}
		System.out.println(sb);
		br.close();
	}
}
