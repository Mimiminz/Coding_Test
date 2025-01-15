import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BOJ 2631 줄세우기
 * @author JOMINJU
 * 
 * 아이들의 숫자와 서 있는 순서가 주어지고, 오름차순으로 아이들을 정렬하기 위해 몇 명의 아이가 움직여야 하는지 구하는 문제
 * 
 * 간단한 문제, 간단한 해결법과 달리 생각하는 데 좀 오래 걸렸다ㅜㅜ
 * DP라고 해서 겁을 잔뜩 집어먹고 1명일 때, 2명일 때, 3명일 때 등 다 적어보며 규칙을 찾으려고 했다.
 * 
 * 그래서 보다가 생각난게 애들이 오름차순으로 서있는 애들이 있고 그 사이에 껴 있는 애들이 있으니 얘네를 어떻게 하면 되겠다 라는 생각이 들었다.
 * 다만 dp라 그래서 또 dp[n] = dp[n-1]+dp[n-2]의 형태인 줄 알고 대체 어떻게 해야지 구할 수 있지? 했는데
 * 그냥 최장길이수열을 구하고 그를 전체에서 빼면 이동해야 하는 아이들의 수가 구해진다.
 * 
 * 그래서 그냥 최장거리 수열만 구하면 되는 문제였다..
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int[] order;
    public static int[] orderLength;

    public static void main(String[] args) throws Exception {
        int number = Integer.parseInt(br.readLine().trim());
        order = new int[number+1];
        orderLength = new int[number+1];

        for(int cnt = 1; cnt <= number; cnt++){
            order[cnt] = Integer.parseInt(br.readLine().trim());
            orderLength[cnt] = 1;
        }

        for(int idx = 1; idx <= number; idx++){
            for(int cnt = 1; cnt < idx; cnt++){
                if(order[idx] > order[cnt]){
                    orderLength[idx] = Math.max(orderLength[idx], orderLength[cnt]+1);
                }
            }
        }

        int result = 0;

        for(int cnt = 1; cnt <= number; cnt++){
            result = Math.max(result, orderLength[cnt]);
        }

        sb.append(number-result);
        System.out.println(sb);

    }
}