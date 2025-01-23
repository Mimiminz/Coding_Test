import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ 17136 색종이 붙이기
 * @author JOMINJU
 * 
 * 1x1부터 5x5까지의 색종이들이 각각 5장 씩 있는데 이 색종이를 최소한만큼 써서 10x10의 종이에 1로 적힌 부분만 덮어야 함.
 * 
 * 내가 생각한 방법
 * 1. 5x5부터 시작해서 붙일 수 있는 색종이 찾기
 *  1-1. 단, 오른쪽과 아래를 살펴서 최대 25개가 다 1이 되면 바로 붙이기
 *  1-2. 2x2까지 반복하고, 1이되면 그냥 1인 개수 찾기
 *  1-3. 이때 5장이 넘어가면 바로 패스 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int[][] maps = new int[10][10];
    public static int[] paper = {0, 5, 5, 5, 5, 5}; 
    public static int result = Integer.MAX_VALUE;

    public static void backtrack(int x, int y, int count) {
        if (x == 10) {
            result = Math.min(result, count);
            return;
        }

        if (y == 10) {
            backtrack(x + 1, 0, count);
            return;
        }

        if (maps[x][y] == 0) {
            backtrack(x, y + 1, count);
            return;
        }

        // 현재 위치에 색종이를 붙이는 모든 경우를 탐색
        for (int size = 5; size >= 1; size--) {
            if (canAttach(x, y, size) && paper[size] > 0) {
                attach(x, y, size, 0); 
                paper[size]--; 
                backtrack(x, y + 1, count + 1);
                attach(x, y, size, 1); 
                paper[size]++;
            }
        }
    }

    public static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false; 

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (maps[x + i][y + j] == 0) return false; 
            }
        }
        return true;
    }

    public static void attach(int x, int y, int size, int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maps[x + i][y + j] = value;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0, 0, 0);

        sb.append(result == Integer.MAX_VALUE ? -1 : result);

        System.out.println(sb);
        br.close();
    }
}
