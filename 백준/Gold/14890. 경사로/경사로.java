import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 14890 경사로
 * @author JOMINJU
 * 
 * 높이 차이가 1이고, 바닥면이 L보다 긴 공간에만 경사로를 놓을 수 있을 때,
 * 이동할 수 있는 길의 개수를 구해라.
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int size;
    static int limit;
    static int[][] maps;

    static int connectRoad(boolean isRow, int number){
        int x = isRow ? 1 : number;
        int y = isRow ? number : 1;
        int dx = isRow ? 1 : 0;
        int dy = isRow ? 0 : 1;

        int count = 1;
        boolean check = false;

        for(int idx = 1; idx < size; idx++){
            if(maps[y-dy][x-dx] +1 == maps[y][x]){
                if(count >= limit){
                    count = 1;
                }else{
                    return 0;
                }
            }else if(maps[y-dy][x-dx] == maps[y][x] + 1){
                if(check){
                    return 0;
                }else{
                    count = 1;
                    check = true;
                }
            }else if(maps[y-dy][x-dx] == maps[y][x]){
                count++;
            }else{
                return 0;
            }

            if(check && count == limit){
                check = false;
                count = 0;
            }

            x += dx;
            y += dy;
        }

        if(check){
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        size = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());
        maps = new int[size][size];

        for(int col = 0; col < size; col++){
            st = new StringTokenizer(br.readLine().trim());
            for(int row = 0; row < size; row++){
                maps[col][row] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for(int idx = 0; idx < size; idx++){
            result += connectRoad(false, idx);
            result += connectRoad(true, idx);
        }

        System.out.println(result);
    }
}
