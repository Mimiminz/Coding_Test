import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 7662 이중 우선순위 큐
 * @author JOMINJU
 * 
 * I : Insert, 큐에 값 삽입
 * D : Delete, 큐의 값 삭제
 *   -> D 1 : 최대값 삭제
 *   -> D -1 : 최소값 삭제
 *   -> 큐가 비어있을 경우 그냥 패스
 * 
 * 중복값 가능, 중복일 시 하나만 삭제
 * 
 */

 public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine().trim());
        PriorityQueue<Integer> minpq = new PriorityQueue<>();
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                if(o1 > o2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        HashMap<Integer, Integer> check = new HashMap<>();
        for(int T = 0; T < testCase; T++){
            minpq.clear();
            maxpq.clear();
            check.clear();
            int deleteNum = 0;
            int addNum = 0;
            int cmdNum = Integer.parseInt(br.readLine().trim());
            for(int cnt = 0; cnt < cmdNum; cnt++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                String cmd = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                // 최소 큐와 최대 큐에 값 넣기
                // hashmap을 사용해서 중복 값이 들어올 경우 value값을 1 높이기
                if(cmd.equals("I")){
                    if(check.containsKey(num)){
                        check.put(num, check.get(num) + 1);
                    }else{
                        check.put(num, 1);
                    }
                    minpq.offer(num);
                    maxpq.offer(num);
                    addNum++;
                }else{ 
                    if(addNum == deleteNum){
                        continue;
                    }

                    deleteNum++;

                    //최대값 삭제인 경우 maxpq에서 제거, map의 현재 개수도 변경 
                    if(num == 1){
                        while(!maxpq.isEmpty()){
                            int pollValue = maxpq.poll();
                            if(check.get(pollValue) >= 1){
                                check.put(pollValue, check.get(pollValue) - 1);
                                break;
                            }
                        }
                    }else{ //최소값 삭제인 경우 minpq에서 제거
                        while(!minpq.isEmpty()){
                            int pollValue = minpq.poll();
                            if(check.get(pollValue) >= 1){
                                check.put(pollValue, check.get(pollValue) - 1);
                                break;
                            }
                        }
                    }
                }
            }

            if(deleteNum == addNum){
                sb.append("EMPTY").append("\n");
            }else{
                int maxValue = 0;
                while(!maxpq.isEmpty()){
                    maxValue = maxpq.poll();
                    if(check.get(maxValue) >= 1){
                        break;
                    }
                }
                int minValue = 0;
                while(!minpq.isEmpty()){
                    minValue = minpq.poll();
                    if(check.get(minValue) >= 1){
                        break;
                    }
                }
                sb.append(maxValue).append(" ").append(minValue).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}