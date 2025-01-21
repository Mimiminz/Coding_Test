import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 2638 치즈
 * @author JOMINJU
 * 
 * bfs로
 * (0,0)부터 시작해서 상하좌우 인접하고, 값이 0인 것들만 들면서 만약 주변에 1인 치즈가 있으면 check에 +1
 * 한번 돌고 나서 만약 check가 2이상이 된 것이 있으면 큐에 넣기
 * 이후 큐에 넣은 것들은 치즈를 표시하는 1값을 0으로 바꾸고, 상하좌우에 인접한 치즈가 있으면 +1하기
 * 계속 반복
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int rowSize;
    public static int colSize;
    public static int[][] cheeses;
    public static int[][] check;
    public static int time;

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static Queue<int[]> air;
    public static Queue<int[]> melt;

    public static void meltCheese(){
        air = new LinkedList<>();
        melt = new LinkedList<>();
        air.offer(new int[]{0,0});
        melt.offer(new int[]{-1,-1});
        while(true){
            while(!air.isEmpty()){
                int[] current = air.poll();

                for(int direct = 0; direct < 4; direct++){
                    int row = current[1] + dx[direct];
                    int col = current[0] + dy[direct];

                    if(row < 0 || col < 0 || row >= rowSize || col >= colSize || cheeses[col][row] == -1){
                        continue;
                    }

                    if(cheeses[col][row] == 0){
                        cheeses[col][row] = -1;
                        air.offer(new int[]{col, row});
                    }else if(cheeses[col][row] == 1){
                        check[col][row]++;
                    }
                }
            }

            for(int col = 0; col < colSize; col++){
                for(int row = 0; row < rowSize; row++){
                    if(check[col][row] >= 2){
                        air.offer(new int[]{col, row});
                        cheeses[col][row] = -1;
                        check[col][row] = 0;
                    }
                }
            }
            time++;

            if(CheckCheese()){
                break;
            }
        }
    }

    public static boolean CheckCheese(){
        for(int col = 0; col < colSize; col++){
            for(int row = 0; row < rowSize; row++){
                if(cheeses[col][row] == 1){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        cheeses = new int[colSize][rowSize];
        check = new int[colSize][rowSize];
        time = 0;

        for(int col = 0; col < colSize; col++){
            st = new StringTokenizer(br.readLine().trim());
            for(int row = 0; row < rowSize; row++){
                cheeses[col][row] = Integer.parseInt(st.nextToken());
            }
        }
        
        meltCheese();
        
        sb.append(time);
        System.out.println(sb);
        br.close();
    }
}
