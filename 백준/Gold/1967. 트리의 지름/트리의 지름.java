import java.io.*;
import java.util.*;

public class Main {
    static int nodeNum;
    static ArrayList<int[]>[] graph;
    static int maxDistance, farthestNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeNum = Integer.parseInt(br.readLine().trim());
        graph = new ArrayList[nodeNum + 1];

        for (int i = 1; i <= nodeNum; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeNum - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[parent].add(new int[]{child, weight});
            graph[child].add(new int[]{parent, weight});
        }

        // 1. 임의의 노드(1번)에서 가장 먼 노드(A)를 찾기
        bfs(1);

        // 2. A에서 가장 먼 노드(B)를 찾고, 지름 계산
        bfs(farthestNode);

        System.out.println(maxDistance);
    }

    static void bfs(int start) {
        boolean[] visited = new boolean[nodeNum + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        maxDistance = 0;
        farthestNode = start;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            if (distance > maxDistance) {
                maxDistance = distance;
                farthestNode = node;
            }

            for (int[] neighbor : graph[node]) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(new int[]{nextNode, distance + weight});
                }
            }
        }
    }
}