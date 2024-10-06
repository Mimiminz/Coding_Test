import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static PriorityQueue<int[]> queue;
    public static int N;
    public static int maxDay;
    public static int[] complete;
    public static int result;

    public static boolean checkDay(){
        for (int i = 1; i <= maxDay; i++) {
            if(complete[i] == 0){
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());
        maxDay = 0;

        queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]){
                    return o2[1] - o1[1];
                }
                return o2[0]-o1[0];
            }
        });

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int score = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            queue.offer(new int[]{score, day});
            maxDay = Math.max(maxDay, day);
        }

        complete = new int[maxDay+1];
        result = 0;

        while (!queue.isEmpty()) {
            if(checkDay()){
                break;
            }

            int[] current = queue.poll();
            if(complete[current[1]] == 0){
                complete[current[1]] = current[0];
            }else{
                for (int no = current[1]; no > 0; no--) {
                    if(complete[no] == 0){
                        complete[no] = current[0];
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= maxDay; i++) {
            result += complete[i];
        }
        
        sb.append(result);
        System.out.println(sb);
        br.close();
    }
}