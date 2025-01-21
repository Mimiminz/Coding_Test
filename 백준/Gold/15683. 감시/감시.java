import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * BOJ 15683 감시
 * @author JOMINJU
 * 
 * 1-5 까지 각기 다른 방향을 감시할 수 있는 감시카메라가 있고, 6은 벽을 나타낸다.
 * 이때 CCTV가 감시하지 못하는 사각지대의 최소 값을 찾아 출력해라.
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int rowSize;
    public static int colSize;
    public static int[][] maps;
    public static int[][] check;
    public static ArrayList<Camera> cameras;
    public static int result;
    public static boolean[][] confirm;

    public static int[][][] cctvTypes = {
        {{1}, {2}, {3}, {4}}, // 1번 카메라
        {{1, 2}, {3, 4}}, // 2번 카메라
        {{1, 4}, {2, 4}, {2, 3}, {1, 3}}, // 3번 카메라
        {{1, 2, 4}, {2, 3, 4}, {1, 2, 3}, {1, 3, 4}}, // 4번 카메라
        {{1, 2, 3, 4}} // 5번 카메라
    };

    static class Camera implements Comparable<Camera>{
        int x;
        int y;
        int type;

        Camera(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Camera o) {
            return Integer.compare(o.type, this.type); // type 값을 기준으로 내림차순 정렬
        }
    }

    public static void checkRange(int x, int y, int[] types, int isCheck){
        // 1번 상, 2번 하, 3번 좌, 4번 우
        for(int cctv: types){
            int row = x;
            int col = y;
            if(cctv == 1){
                if(confirm[cctv][row]){
                    continue;
                }
                while(true){
                    col--;
                    if(col < 0 || maps[col][row] == 6){
                        break;
                    }
                    if(isCheck == 10){
                        confirm[cctv][row] = true;
                    }
                    check[col][row]+=isCheck;
                }                   
            }else if(cctv == 2){
                if(confirm[cctv][row]){
                    continue;
                }
                while(true){
                    col++;
                    if(col >= colSize || maps[col][row] == 6){
                        break;
                    }
                    check[col][row]+=isCheck;
                }         
            }else if(cctv == 3){
                if(confirm[cctv][col]){
                    continue;
                }
                while(true){
                    row--;
                    if(row < 0 || maps[col][row] == 6){
                        break;
                    }
                    if(isCheck == 10){
                        confirm[cctv][col] = true;
                    }
                    check[col][row]+=isCheck;
                }    
            }else if(cctv == 4){
                if(confirm[cctv][col]){
                    continue;
                }
                while(true){
                    row++;
                    if(row >= rowSize || maps[col][row] == 6){
                        break;
                    }
                    check[col][row]+=isCheck;
                }    
            }
        }
    }

    public static void findBlindSpot(int number){
        if(number == cameras.size()){
            checkBlindSpot();
            if(result == 0){
                return;
            }
        }
        for(int idx = number; idx < cameras.size(); idx++){
            if(cameras.get(idx).type == 5){
                checkRange(cameras.get(idx).x, cameras.get(idx).y, cctvTypes[4][0], 7);
            }else{
                for(int[] types: cctvTypes[cameras.get(idx).type -1]){
                    checkRange(cameras.get(idx).x, cameras.get(idx).y, types, 1);
                    findBlindSpot(idx+1);
                    checkRange(cameras.get(idx).x, cameras.get(idx).y, types, -1);
                }
            }
        }
    }

    public static void checkBlindSpot(){
        int spots = 0;
        for(int col = 0; col < colSize; col++){
            for(int row = 0; row < rowSize; row++){
                if(check[col][row] == 0 && maps[col][row] == 0){
                    spots++;
                }
            }
        }
        result = Math.min(result, spots);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        maps = new int[colSize][rowSize];
        check = new int[colSize][rowSize];
        confirm = new boolean[5][colSize > rowSize ? colSize : rowSize];
        cameras = new ArrayList<>();
        result = 987654321;

        for(int col = 0; col < colSize; col++){
            st = new StringTokenizer(br.readLine().trim());
            for(int row = 0; row < rowSize; row++){
                maps[col][row] = Integer.parseInt(st.nextToken());
                if(maps[col][row] != 0 && maps[col][row] != 6){
                    cameras.add(new Camera(row, col, maps[col][row]));
                }
            }
        }
        Collections.sort(cameras);
        
        findBlindSpot(0);
        checkBlindSpot();
        sb.append(result);
        System.out.println(sb);
        br.close();
    }
}