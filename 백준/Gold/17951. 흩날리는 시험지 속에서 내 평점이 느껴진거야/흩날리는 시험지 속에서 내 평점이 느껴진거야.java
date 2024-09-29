import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static final int MAX = 987654321;

    public static int num;
    public static int interval;
    public static int[] arr;

    public static int left;
    public static int right;
    public static int mid;

    public static int count;
    public static int score;
    public static int minScore;

    public static boolean solve(int mid){
        count = 1;
        score = 0;
        for(int idx = 0; idx < num; idx++) {
            score += arr[idx];

            if(score > mid) {
                minScore = Math.min(minScore, score);
                score = 0;
                count++;
            }
        }

        return interval >= count;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        interval = Integer.parseInt(st.nextToken());
        arr = new int[num];
        left = 0;
        right = 0;
        minScore = MAX;

        st = new StringTokenizer(br.readLine().trim());
        
        for (int idx = 0; idx < num; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
            right += arr[idx];
        }
        
        while(left <= right){
            mid = (left + right)/2;

            if(solve(mid)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        sb.append(left);
        System.out.println(sb);
        br.close();
    }
}