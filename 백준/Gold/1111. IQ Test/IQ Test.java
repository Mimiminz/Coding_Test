import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1111 IQ Test
 * @author JOMINJU
 * 
 * a만큼 구하고 b를 더하기 때문에 구하기 어렵지는 않은 문제
 * 다만 예외 사항이 꽤 많고 구별해야 할 경우가 많다.
 * 
 */

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int multi;
    public static int add;
    public static int count;
    public static int[] arr;

    public static void main(String[] args) throws Exception {
        count = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[count];

        for (int idx = 0; idx < count; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        if (count == 1) {
            System.out.println("A");
            return;
        }

        if (count == 2) {
            System.out.println(arr[0] == arr[1] ? arr[0] : "A");
            return;
        }

        if (arr[1] - arr[0] == 0) {
            multi = 0;
            add = arr[1];
        } else if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
            System.out.println("B");
            return;
        } else {
            multi = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            add = arr[1] - arr[0] * multi;
        }

        // 규칙을 전부 따르는지 확인
        for (int idx = 0; idx < count - 1; idx++) {
            if (arr[idx + 1] != arr[idx] * multi + add) {
                System.out.println("B");
                return;
            }
        }

        sb.append(arr[count - 1] * multi + add);
        System.out.println(sb);
    }
}
