import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int number;
    public static int pairNum;
    public static int result;

    public static int[][] nodes;
    public static boolean[] visited;

    public static void distance(int start, int end, int depth){
        for (int num = 1; num <= number; num++) {
            if(!visited[num] && nodes[start][num] != 0){
                if(num == end){
                    result = depth + nodes[start][num];
                    return;
                }
                visited[num] = true;
                distance(num, end, depth + nodes[start][num]);
            }

            if(result != 0){
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        number = Integer.parseInt(st.nextToken());
        pairNum = Integer.parseInt(st.nextToken());
        nodes = new int[number+1][number+1];
        result = 0;

        // 노드 간 거리
        for (int node = 0; node < number-1; node++) {
            st = new StringTokenizer(br.readLine().trim());
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            nodes[firstNode][secondNode] = dis;
            nodes[secondNode][firstNode] = dis;
        }

        // 거리를 알고 싶은 노드의 쌍
        for (int pair = 0; pair < pairNum; pair++) {
            result = 0;
            visited = new boolean[number+1];
            st = new StringTokenizer(br.readLine().trim());
            int first = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            visited[first] = true;
            distance(first, last, 0);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}