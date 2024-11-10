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

        st = new StringTokenizer(br.readLine().trim());
        int length = Integer.parseInt(st.nextToken());
        int limitSum = Integer.parseInt(st.nextToken());
        int[] numbers = new int[length];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < length; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sumValue = numbers[0];
        int result = Integer.MAX_VALUE;
        while(start <= end){
            if(sumValue >= limitSum){
                result = Math.min(end - start + 1, result);
                sumValue -= numbers[start];
                start++;
            }else{
                if(end >= length -1){
                    break;
                }
                end++;
                sumValue += numbers[end];
            }
        }

        if(result == Integer.MAX_VALUE){
            result = 0;
        }

        sb.append(result);
        
        System.out.println(sb);
        br.close();
    }
}