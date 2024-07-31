import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 조민주
 *         14502 연구소
 * 
 *         # 0 : 빈칸, 1 : 벽, 2 : 바이러스
 * 
 *         1. 전체를 dfs로 돌면서 벽을 세개 세울 수 있는 전체 경우의 수 구하기
 *         2. bfs를 돌면서 안전지대를 체크 하는데...일단 queue에는 상하좌우에 0이 하나라도 있는 위치만 집어넣는다.
 */

public class Main {

   public static BufferedReader br;
   public static StringBuilder sb;
   public static StringTokenizer st;

   public static int height;
   public static int width;
   public static int[][] map;
   public static int[][] cloneMap;
   public static int wallCount;
   public static int result;

   public static Queue<int[]> queue = new LinkedList<>();
   public static ArrayList<int[]> virusLocation;

   public static int[] dx = { -1, 0, 1, 0 };
   public static int[] dy = { 0, -1, 0, 1 };

   public static void setupWall(int depth) {
      if (depth == 3) {
         setVirus();
         return;
      }

      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {
            if (map[y][x] == 0) {
               map[y][x] = 1;
               setupWall(depth + 1);
               map[y][x] = 0;
            }
         }
      }
   }

   public static void setVirus() {

      for (int i = 0; i < virusLocation.size(); i++) {
         queue.offer(virusLocation.get(i));
      }

      setMap();

      while (!queue.isEmpty()) {
         int[] current = queue.poll();
         for (int direct = 0; direct < 4; direct++) {
            int x = current[0] + dx[direct];
            int y = current[1] + dy[direct];

            if (x < 0 || y < 0 || x >= width || y >= height || cloneMap[y][x] != 0) {
               continue;
            }

            queue.offer(new int[] { x, y });
            cloneMap[y][x] = 2;
         }

      }

      findSafeRegion();
   }

   public static void findSafeRegion() {
      int count = 0;
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            if (cloneMap[i][j] == 0) {
               count++;
            }
         }
      }

      result = Math.max(result, count);
      setMap();
   }

   public static void setMap() {
      cloneMap = new int[height][width];

      for (int i = 0; i < map.length; i++) {
         cloneMap[i] = map[i].clone();
      }
   }

   public static void main(String[] args) throws IOException {
      br = new BufferedReader(new InputStreamReader(System.in));
      sb = new StringBuilder();
      st = new StringTokenizer(br.readLine().trim());

      height = Integer.parseInt(st.nextToken());
      width = Integer.parseInt(st.nextToken());
      map = new int[height][width];
      virusLocation = new ArrayList<>();
      result = Integer.MIN_VALUE;
      cloneMap = new int[height][width];

      for (int y = 0; y < height; y++) {
         st = new StringTokenizer(br.readLine().trim());
         for (int x = 0; x < width; x++) {
            map[y][x] = Integer.parseInt(st.nextToken());

            if (map[y][x] == 2) {
               virusLocation.add(new int[] { x, y });
            }
         }
      }

      setupWall(0);

      sb.append(result);
      System.out.println(sb);
      br.close();

   }
}