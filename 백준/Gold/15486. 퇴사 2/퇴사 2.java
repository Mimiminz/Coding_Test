import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 15486 퇴사2
 * @author JOMINJU
 * 
 * 누가 봐도 dp 문제다. 근데 그냥 dp문제인 것만 알겠다.
 * 상담을 무사히 했을 경우에 끝난 날짜에 비용 더하기.
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int lastDay;
    public static int[][] conver;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        lastDay = Integer.parseInt(br.readLine().trim());
        conver = new int[lastDay+2][2];
        dp = new int[lastDay+2];

        for(int idx = 1; idx <= lastDay; idx++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int delay = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            conver[idx][0] = delay;
            conver[idx][1] = money;
        }

        int maxValue = -1;
        for (int idx = 1; idx <= lastDay+1; idx++) {
            if(maxValue < dp[idx]){
                maxValue = dp[idx];
            }
            int nxtDay = idx + conver[idx][0];
            if(nxtDay < lastDay+2){
                dp[nxtDay] = Math.max(dp[nxtDay], maxValue+conver[idx][1]);
            }
        }
        System.out.println(dp[lastDay+1]);
    }
}
