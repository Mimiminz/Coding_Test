import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
        	if(Math.abs(o1) != Math.abs(o2))
        		return Integer.compare(Math.abs(o1),Math.abs(o2));
        	return Integer.compare(o1,o2);
        }
    });

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int number = Integer.parseInt(br.readLine().trim());
		//PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int idx = 0; idx < number; idx++) {
			int x = Integer.parseInt(br.readLine().trim());
			if(x == 0) {
				if(!minHeap.isEmpty())
					sb.append(minHeap.remove()).append("\n");
				else
					sb.append(0).append("\n");
			}else {
				minHeap.add(x);
			}
		}
		System.out.println(sb);
        br.close();
	}
}