import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @author 조민주
 *         7576 토마토
 * 
 *         1. 사방을 채워가며 박스를 빠르게 전부 다 훑어야 한다(dfs)
 *         2. 토마토가 익지 않은 공간 (값이 0) 만 확인해야 한다.
 *         3. 사방으로 가면서 확인해야한다.
 *         4.
 */

public class Main {

   public static BufferedReader br;
   public static StringBuilder sb;
   public static StringTokenizer st;

   // 우, 하, 좌, 상
   public static int[] dx = { 1, 0, -1, 0 };
   public static int[] dy = { 0, 1, 0, -1 };
   public static int[][] box;
   public static int count;
   public static int width;
   public static int height;
   public static Queue<int[]> que;

   static void growsTomato() {
      while (!que.isEmpty()) {
         int[] current = que.poll();
         count = Math.max(count, current[2]);
         for (int direct = 0; direct < 4; direct++) {
            int x = current[0] + dx[direct];
            int y = current[1] + dy[direct];
            if (x < 0 || y < 0 || x >= width || y >= height || box[y][x] != 0) {
               continue;
            }

            if (box[y][x] == 0) {
               box[y][x] = 1;
               que.offer(new int[] { x, y, current[2] + 1 });
            }
         }
      }
   }

   public static void main(String[] args) throws IOException {
      br = new BufferedReader(new InputStreamReader(System.in));
      sb = new StringBuilder();
      st = new StringTokenizer(br.readLine().trim());

      width = Integer.parseInt(st.nextToken());
      height = Integer.parseInt(st.nextToken());
      box = new int[height][width];
      count = 0;

      que = new LinkedList<int[]>();

      for (int cntY = 0; cntY < height; cntY++) {
         st = new StringTokenizer(br.readLine().trim());
         for (int cntX = 0; cntX < width; cntX++) {
            box[cntY][cntX] = Integer.parseInt(st.nextToken());

            if (box[cntY][cntX] == 1) {
               que.offer(new int[] { cntX, cntY, 0 });
            }
         }
      }

      growsTomato();

      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {
            if (box[y][x] == 0) {
               count = -1;
            }
         }
      }

      sb.append(count);
      System.out.println(sb);

   }
}
