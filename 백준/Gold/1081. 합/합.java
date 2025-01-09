import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1081 합
 * @author JOMINJU
 *
 * L과 U라는 정수가 주어진다. L 이상 U 이하의 모든 정수의 각 자리의 합을 구해라.
 * 
 * 
 * 0-9까지의 합은 45
 * 0-L까지의 각 자리의 합을 구하고 0-U까지의 각 자리의 합을 구해서 빼기
 * @구해야 할 것: 0-N까지의 각 자리의 합.
 * 10의 n제곱까지 각 자리의 합이 얼마나 되는지 구하고 그 나머지만 따로 구하기
 * 
 * 10의 n제곱까지의 각 자리의 합을 구하는 방법
 * 45 * 10 ^ n + [n-1의 각 자리의 합] * 10
 * 
 */

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static long[] arr = new long[10];   // 누적 합 배열
    public static long[] powers = new long[10]; // 10의 제곱 배열

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());

        precompute(); // arr[]와 powers[] 계산
        long sumL = sum(L - 1);
        long sumU = sum(U);
        sb.append(sumU - sumL);
        System.out.println(sb);
    }

    public static void precompute() {
        arr[1] = 45; // 0-9의 합
        powers[0] = 1;

        // 각 자리수 별 구하는 공식
        for (int i = 1; i < 10; i++) {
            powers[i] = powers[i - 1] * 10; 
            arr[i] = 45 * powers[i - 1] + arr[i - 1] * 10;
        }
    }

    public static long sum(long n) {
        if (n <= 0) return 0;

        int pow = (int) Math.log10(n); // n의 자리수 eg)2345일 경우 pow = 3
        long base = powers[pow];       // 10^pow
        long firstDigit = n / base;    // 맨 앞자리수 eg) 2345일 경우 2
        long remainder = n % base;     // 나머지 수 eg) 2345일 경우 345

        long res = (arr[pow] * firstDigit)  // 이전 자릿수들의 누적 합
                 + (firstDigit * (remainder + 1)) // 맨 앞자리수의 기여
                 + (firstDigit * (firstDigit - 1) / 2 * base); // 0~r-1 반복 합

        return res + sum(remainder);
    }
}
