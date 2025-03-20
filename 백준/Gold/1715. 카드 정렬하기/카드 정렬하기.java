import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * BOJ 1715 카드 정렬하기
 * @author JOMINJU
 * 
 * 최대한 적은 카드부터 먼저 더하고, 그 다음으로 큰거를 더하는 방식으로
 * 우선순위 큐를 이용해서 가장 작은 값부터 두 개를 빼고, 그 두개를 더한 값을 다시 큐에 넣는다.
 * 그리고 이를 큐가 빌 때까지 반복
 * 
 */

 public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine().trim());
        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int cnt = 0; cnt < num; cnt++){
            pq.offer(Integer.parseInt(br.readLine().trim()));
        }

        if(num == 1){
            System.out.println(0);
            return;
        }

        while(!pq.isEmpty()){
            int first = pq.poll();
            int second = pq.poll();

            int mix = first+second;
            result += mix;

            if(pq.isEmpty()){
                sb.append(result);
                break;
            }else{
                pq.offer(mix);
            }
        }
        System.out.println(sb);
        br.close();
    }

}