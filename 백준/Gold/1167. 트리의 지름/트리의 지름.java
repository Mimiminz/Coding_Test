import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BOJ 1167 트리의 지름
 * @author JOMINJU
 * 
 * 모든 노드에서 노드 사이의 거리를 구하고, 최대값을 출력하기
 * 
 */

 public class Main {
    static int nodeNum;
    static ArrayList<Node>[] graph;
    static int maxDistance, farthestNode;

    static class Node{
        int idx;
        int weight;

        Node(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeNum = Integer.parseInt(br.readLine().trim());
        graph = new ArrayList[nodeNum + 1];

        for (int i = 1; i <= nodeNum; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int parent = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int child = Integer.parseInt(st.nextToken());
                if(child == -1)     break;
                int weight = Integer.parseInt(st.nextToken());

                graph[parent].add(new Node(child, weight));
            }
        }

        bfs(1);
        bfs(farthestNode);

        System.out.println(maxDistance);
    }

    static void bfs(int start) {
        boolean[] visited = new boolean[nodeNum + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;

        maxDistance = 0;
        farthestNode = start;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.weight > maxDistance) {
                maxDistance = current.weight;
                farthestNode = current.idx;
            }

            for (Node neighbor : graph[current.idx]) {

                if (!visited[neighbor.idx]) {
                    visited[neighbor.idx] = true;
                    queue.add(new Node(neighbor.idx, current.weight + neighbor.weight));
                }
            }
        }
    }
}