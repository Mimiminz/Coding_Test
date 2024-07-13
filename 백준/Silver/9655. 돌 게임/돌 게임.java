import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //StringTokenizer st = new StringTokenizer(br.readLine());
        //int n = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 1;
        for (int i = 4; i <= count; i++) {
            dp[i] = Math.min(dp[i-1],dp[i-3]) + 1;
        }
        if(dp[count]%2 == 0){
            sb.append("CY");
        }else{
            sb.append("SK");
        }
        System.out.println(sb);
    }
}