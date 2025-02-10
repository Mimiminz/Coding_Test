import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BOJ 13265 색칠하기
 * @author JOMINJU
 * 
 * 이분그래프인지 아닌지 확인하기
 * ArrayList에 인접한 정점들 넣기
 * 이미 방문한 정점일 때 색깔이 햔재 정점과 같을 경우 impossible출력
 * 방문하지 않은 정점일 때 색깔을 반대로 칠하고 큐에 넣기
 * 큐가 빌때까지 반복
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer>[] graph;
    static int nodeNum;
    static int edgeNum;
    static int[] color;

    static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        // 0은 색 없음 -1은 블랙 1은 레드
        color[1] = -1;

        while(!que.isEmpty()){
            int current = que.poll();
            for(int connect : graph[current]){
                if(color[connect] == 0){
                    color[connect] = color[current] * -1;
                    que.add(connect);
                }else if(color[connect] == color[current]){
                    sb.append("impossible").append("\n");
                    return;
                }
            }

            if(que.isEmpty()){
                for(int idx = 1; idx <= nodeNum; idx++){
                    if(color[idx] == 0){
                        que.add(idx);
                        color[idx] = -1;
                        break;
                    }
                }
            }
        }

        sb.append("possible").append("\n");
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());
        for(int T = 0; T < testCase; T++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            nodeNum = Integer.parseInt(st.nextToken());
            edgeNum = Integer.parseInt(st.nextToken());

            if(edgeNum == 0){
                sb.append("possible").append("\n");
                continue;
            }
            graph = new ArrayList[nodeNum+1];
            color = new int[nodeNum+1];

            for(int idx = 1; idx <= nodeNum; idx++){
                graph[idx] = new ArrayList<>();
            }

            for(int edge = 0; edge < edgeNum; edge++){
                st = new StringTokenizer(br.readLine().trim());
                int one = Integer.parseInt(st.nextToken());
                int two = Integer.parseInt(st.nextToken());
                graph[one].add(two);
                graph[two].add(one);
            }

            bfs();
        }
        System.out.println(sb);
    }
}