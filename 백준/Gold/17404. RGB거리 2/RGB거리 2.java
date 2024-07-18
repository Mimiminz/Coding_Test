import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class Main {
    public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
    public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

        int homeNumber = Integer.parseInt(br.readLine().trim());
        int[][] money = new int[homeNumber][3];
        int[][] dp = new int[homeNumber][3];
        int[] result = new int[3];
        //int minValue = 1001;
        for(int cnt = 0; cnt < homeNumber; cnt++){
            st = new StringTokenizer(br.readLine().trim());
            money[cnt][0] = Integer.parseInt(st.nextToken());
            money[cnt][1] = Integer.parseInt(st.nextToken());
            money[cnt][2] = Integer.parseInt(st.nextToken());
        }
        for(int color = 0; color < 3; color++){
            for(int paint = 0; paint < 3; paint++){
                if(color == paint){
                    dp[0][paint] = money[0][paint];
                }else{
                    dp[0][paint] = 1001;
                }
            }

            for(int idx = 1; idx < homeNumber; idx++){
                dp[idx][0] = Math.min(dp[idx-1][1], dp[idx-1][2]) + money[idx][0];
                dp[idx][1] = Math.min(dp[idx-1][0], dp[idx-1][2]) + money[idx][1];
                dp[idx][2] = Math.min(dp[idx-1][0], dp[idx-1][1]) + money[idx][2];

                if(idx == (homeNumber -1)){
                    if(color == 0){
                        result[color] = Math.min(dp[idx][1], dp[idx][2]);
                    }
                    if(color == 1){
                        result[color] = Math.min(dp[idx][0], dp[idx][2]);
                    }
                    if(color == 2){
                        result[color] = Math.min(dp[idx][0], dp[idx][1]);
                    }
                    //result = Math.min(result, minValue);
                }
            }
        }
        sb.append(Math.min(result[0],Math.min(result[1],result[2])));
        System.out.println(sb);
    }
}