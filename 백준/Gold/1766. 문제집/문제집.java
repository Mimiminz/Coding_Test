import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static PriorityQueue<Integer> prique;
    public static ArrayList<Integer>[] nodes;

    public static int questionNum;
    public static int infoNum;

    public static int[] solved;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        questionNum = Integer.parseInt(st.nextToken());
        infoNum = Integer.parseInt(st.nextToken());
        prique = new PriorityQueue<Integer>();

        int minNum = 1;

        nodes = new ArrayList[questionNum+1];
        solved = new int[questionNum+1];
        for (int i = 0; i <= questionNum; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        
        for (int idx = 0; idx < infoNum; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            nodes[before].add(after);
            solved[after]++;
        }
        
        // 선행해야 할 문제가 없는 먼저 풀어야 하는 문제들만 큐에 넣기
        for (int idx = 1; idx <= questionNum; idx++) {
            if(nodes[idx].size() > 0 && solved[idx] == 0){
                prique.offer(idx);
            }
        }

        // 먼저 풀어야 하는 문제가 없을 때까지 반복
        while(!prique.isEmpty()){
            
            for (int idx = minNum; idx <= questionNum; idx++) {
                if(solved[idx] == -1){
                    continue;
                }else {
                    minNum = idx;
                    break;
                }
            }

            for (int idx = minNum; idx < prique.peek(); idx++) {
                if(solved[idx] == 0){
                    sb.append(idx).append(" ");
                    solved[idx] = -1;
                }
            }
            

            int current = prique.poll();
            // 푼 문제는 -1로 표기
            solved[current] = -1;
            sb.append(current).append(" ");
            for (int idx = 0; idx < nodes[current].size(); idx++) {
                // 미리 풀어야 하는 문제의 개수 1개 줄이기
                solved[nodes[current].get(idx)]--;
                // 미리 풀어야 하는 문제가 없어졌을 경우 큐에 넣기
                if(solved[nodes[current].get(idx)] == 0){
                    prique.offer(nodes[current].get(idx));
                }
            }

            nodes[current].clear();
        }

        for (int idx = 1; idx <= questionNum; idx++) {
            if(solved[idx] == -1){
                continue;
            }

            sb.append(idx).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}