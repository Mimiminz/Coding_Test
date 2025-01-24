import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1976 여행가자
 * @author JOMINJU
 * 
 *  여행가려는 도시가 길이 연결되어 있지 아닌지 확인하는 문제
 * 시간은 상관없음.
 * 연결의 유무만 파악 -> 간단한 다익스트라 이용
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int cityNum, planNum;
    public static ArrayList<Node>[] connect;
    public static boolean[] visited; 
    public static int[] goal; 

    public static boolean[][] isConnect;
    public static int INF = 987654321;

    static class Node {
        int end;
        boolean isConnect;

        Node(int end, boolean isConnect){
            this.end = end;
            this.isConnect = isConnect;
        }
    }

    public static void main(String[] args) throws IOException {
        cityNum = Integer.parseInt(br.readLine().trim());
        planNum = Integer.parseInt(br.readLine().trim());
        connect = new ArrayList[cityNum];

        for (int i = 0; i < cityNum; i++)  connect[i] = new ArrayList<>();

        isConnect = new boolean[cityNum][cityNum];

        for (int i = 0; i < cityNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < cityNum; j++) {
                int con = Integer.parseInt(st.nextToken());
                connect[i].add(new Node(j, con == 1 ? true : false));
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        goal = new int[planNum];
        for(int idx = 0; idx  < planNum; idx++){
            goal[idx] = Integer.parseInt(st.nextToken());
        }

        dijkstra();

        System.out.println(checkConnect());
    }

    public static void dijkstra() {
        Queue<Node> pq = new LinkedList<>();
        visited = new boolean[cityNum];

        for(int idx = 0; idx < cityNum; idx++){
            Arrays.fill(isConnect[idx], false);

            isConnect[idx][idx] = true;
            pq.offer(new Node(idx, true));
            
            while(!pq.isEmpty()){
                int curCity = pq.poll().end;
                if(visited[curCity]){
                    continue;
                }
                
                visited[curCity] = true;
                for(Node p : connect[curCity]){
                    if(!isConnect[idx][p.end] && isConnect[idx][curCity] && p.isConnect){
                        isConnect[idx][p.end] = true;
                        pq.offer(p);
                    }
                }
            }
            Arrays.fill(visited, false);
        }
    }

    public static String checkConnect() {
        for(int idx = 0; idx < planNum-1; idx++){
            if(!isConnect[goal[idx]-1][goal[idx+1]-1]){
                return "NO"; 
            }
        }

        return "YES";
    }

}
