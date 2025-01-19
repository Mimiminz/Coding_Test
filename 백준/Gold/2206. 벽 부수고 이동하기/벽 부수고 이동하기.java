import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 2206 벽부수고 이동하기
 * @author JOMINJU
 * 
 * 그냥 dfs 문제로 풀어도 되지 않을까?
 * 근데 이제 파라미터에 벽 부숨의 유무까지 설정해서
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int rowSize;
    public static int colSize;
    public static char[][] maps;
    public static boolean[][][] check;

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int result;

    static class Node{
        int x;
        int y;
        boolean isDestroy;
        int depth;

        Node(int x, int y, boolean isDestroy, int depth){
            this.x = x;
            this.y = y;
            this.isDestroy = isDestroy;
            this.depth = depth;
        }
    }

    public static void findRoute(){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0,false, 1));

        while(!queue.isEmpty()){
            Node current = queue.poll();

            if(current.x == rowSize-1 && current.y == colSize-1){
                result = Math.min(result, current.depth);
            }

            for(int direct = 0; direct < 4; direct++){
                int row = current.x + dx[direct];
                int col = current.y + dy[direct];
    
                if(row < 0 || col < 0 || row >= rowSize || col >= colSize || check[col][row][current.isDestroy ? 1 : 0]){
                    continue;
                }
    
                check[col][row][current.isDestroy ? 1 : 0] = true;

                if(maps[col][row] == '1' && !current.isDestroy){
                    queue.offer(new Node(row, col, true, current.depth+1));
                }else if(maps[col][row] == '0'){
                    queue.offer(new Node(row, col, current.isDestroy, current.depth+1));
                }
    
            } 
        }


    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        maps = new char[colSize][rowSize];
        check = new boolean[colSize][rowSize][2];
        result = 987654321;

        for(int cidx = 0; cidx < colSize; cidx++){
            String str = br.readLine().trim();
            for(int ridx = 0; ridx < rowSize; ridx++){
                maps[cidx][ridx] = str.charAt(ridx);
            }
        }

        check[0][0][0] = true;
        findRoute();

        if(result == 987654321){
            result = -1;
        }
        sb.append(result);
        System.out.println(sb);

    }
}