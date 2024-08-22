import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 조민주
 *         1115. 순열
 * 
 *         - 원래 순열에서 생길 수 있는 사이클의 수를 구한다.
 *         - 1개 이상일 경우 그게 완벽한 순열과의 차이 값.
 *         - 사이클이 1개일 경우 완전하게 동일한 순열이기 때문에 차이는 0이다.
 * 
 */

public class Main {

   public static BufferedReader br;
   public static StringBuilder sb;
   public static StringTokenizer st;

   public static void main(String[] args) throws IOException {
      br = new BufferedReader(new InputStreamReader(System.in));
      sb = new StringBuilder();

      int size = Integer.parseInt(br.readLine().trim());
      int[] permutation = new int[size];
      boolean[] visit = new boolean[size];

      st = new StringTokenizer(br.readLine().trim());

      for (int cnt = 0; cnt < size; cnt++) {
         permutation[cnt] = Integer.parseInt(st.nextToken());
      }

      int count = 0;
      for (int cnt = 0; cnt < size; cnt++) {
         if (visit[cnt]) {
            continue;
         }

         int start = cnt;
         int current = cnt;
         while (true) {
            visit[current] = true;
            if (permutation[current] == start) {
               break;
            }

            current = permutation[current];
         }

         count++;
      }

      sb.append(count == 1 ? 0 : count);
      System.out.println(sb);
      br.close();

   }
}
