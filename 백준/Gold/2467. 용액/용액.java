import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 2467 용액
 * @author JOMINJU
 * 
 * 이분 탐색을 이용해서 target (현재 목표에 -를 곱한 값과 가장 비슷한 것)을 찾기
 * 만약 -를 곱한 값과 동일한 값을 찾으면 0을 출력하고 return
 * 같지 않을 경우 차이가 가장 적은 값을 찾아서 min값을 출력하기
 */

 public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine().trim());
        int[] liquid = new int[number];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int cnt = 0; cnt < number; cnt++){
            liquid[cnt] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = number - 1;

        int minDiff = Integer.MAX_VALUE;
        int answerLeft = 0;
        int answerRight = 0;

        while (left < right) {
            int sum = liquid[left] + liquid[right];

            if (Math.abs(sum) < minDiff) {
                minDiff = Math.abs(sum);
                answerLeft = liquid[left];
                answerRight = liquid[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answerLeft + " " + answerRight);
    }
}