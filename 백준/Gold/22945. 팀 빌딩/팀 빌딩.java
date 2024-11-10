import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int developerNum = Integer.parseInt(br.readLine().trim());
        int[] developers = new int[developerNum+1];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= developerNum; i++) {
            developers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = developerNum;
        int result = 0;
        while (start <= end) {
            int minValue = Math.min(developers[start], developers[end]);
            result = Math.max(result, minValue * (end - start - 1));
            if(developers[start] < developers[end]){
                start++;
            }else{
                end--;
            }
        }
        sb.append(result);
        
        System.out.println(sb);
        br.close();
    }
}