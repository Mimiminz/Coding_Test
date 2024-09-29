import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static final int MAX = 10001;

    public static int num;
    public static int interval;
    public static int[] arr;

    public static int left;
    public static int right;
    public static int mid;

    public static int count;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        num = Integer.parseInt(st.nextToken());
        interval = Integer.parseInt(st.nextToken());
        arr = new int[num];
        left = 0;
        right = 0;
        
        st = new StringTokenizer(br.readLine().trim());
        
        for (int idx = 0; idx < num; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[idx]);
        }
        
        while(left < right){
            mid = (left + right)/2;

            count = 1;
            int min = MAX;
            int max = -MAX;
            for(int i=0; i < num; i++) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
                if(max - min > mid) {
                    count++;
                    max = -MAX; min = MAX;
                    i--;
                }
            }

            if(interval >= count){
                right = mid;
            }else{
                left = mid + 1;
            }
            }

        sb.append(right);
        System.out.println(sb);
        br.close();
    }
}