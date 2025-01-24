import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 17182 우주 탐사선
 * @author JOMINJU
 * 
 * 행성의 개수와 각 행성에서 행성까지의 거리가 주어졌을 때, 모든 행성을 탐사하기 위한 최소 시간 구하기
 * 
 * 1. 다익스트라 알고리즘을 통해 행성에서 행성까지 걸리는 최소 시간 구하기
 * 2. dfs 알고리즘을 통해 모든 행성을 돌아보기까지의 최소 시간 구하기
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int plantNum, startLocate, result;
    public static ArrayList<Node>[] distance;
    public static boolean[] visited; 

    public static int[][] shortest;
    public static int INF = 987654321;

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        plantNum = Integer.parseInt(st.nextToken());
        startLocate = Integer.parseInt(st.nextToken());
        distance = new ArrayList[plantNum];
        for (int i = 0; i < plantNum; i++)  distance[i] = new ArrayList<>();

        shortest = new int[plantNum][plantNum];
        result = INF;

        for (int i = 0; i < plantNum; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < plantNum; j++) {
                int weight = Integer.parseInt(st.nextToken());
                distance[i].add(new Node(j, weight));
            }
        }

        dijkstra();
        visited[startLocate] = true;
        dfs(startLocate, 0, 1);

        System.out.println(result);
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[plantNum];

        for(int idx = 0; idx < plantNum; idx++){
            Arrays.fill(shortest[idx], INF);

            shortest[idx][idx] = 0;
            pq.offer(new Node(idx, 0));
            
            while(!pq.isEmpty()){
                int curPlant = pq.poll().end;
                if(visited[curPlant]){
                    continue;
                }
                
                visited[curPlant] = true;
                for(Node p : distance[curPlant]){
                    if(shortest[idx][p.end] > shortest[idx][curPlant] + p.weight){
                        shortest[idx][p.end] = shortest[idx][curPlant] + p.weight;
                        pq.offer(p);
                    }
                }
            }
            Arrays.fill(visited, false);
        }
    }

    public static void dfs(int startPlant, int curWeight, int checkNum) {
        if(checkNum == plantNum){
            result = Math.min(result, curWeight);
            return;
        }

        if(curWeight > result){
            return;
        }

        for(int idx = 0; idx < plantNum; idx++){
            if(visited[idx]){
                continue;
            }

            visited[idx] = true;
            dfs(idx, curWeight + shortest[startPlant][idx], checkNum + 1);
            visited[idx] = false;
        }
    }
}
