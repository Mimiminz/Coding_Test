import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] arr = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (count == 1) {
            // 수가 1개면 다음 수를 알 수 없음
            System.out.println("A");
            return;
        }
        if (count == 2) {
            // 수가 2개면 여러 해석 가능
            System.out.println(arr[0] == arr[1] ? arr[0] : "A");
            return;
        }

        // a와 b 계산
        Integer a = null, b = null;
        if (arr[1] - arr[0] != 0) {
            if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
                System.out.println("B");
                return;
            }
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - arr[0] * a;
        } else {
            // 모든 숫자가 같은 경우
            a = 0;
            b = arr[0];
        }

        // 모든 숫자가 a, b 규칙을 따르는지 확인
        for (int i = 0; i < count - 1; i++) {
            if (arr[i + 1] != arr[i] * a + b) {
                System.out.println("B");
                return;
            }
        }

        // 다음 숫자가 유일한지 확인
        int next = arr[count - 1] * a + b;
        boolean isConsistent = true;

        for (int i = 0; i < count - 2; i++) {
            if (arr[i + 1] != arr[i] * a + b) {
                isConsistent = false;
                break;
            }
        }

        if (isConsistent) {
            System.out.println(next);
        } else {
            System.out.println("A");
        }
    }
}
