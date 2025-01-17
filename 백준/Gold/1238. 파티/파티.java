import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 1238 파티
 * @author JOMINJU
 * 
 * 문제를 읽어보면 전형적인 다익스트라 알고리즘 문제다.
 * N개의 마을에 각 1명씩 학생이 사는데, 이들이 K 마을에서 모이기로 했다.
 * 근데 각 마을에서 마을로 이동할 수 있는 M개의 단방향 도로가 있는데, 오고 가는데 가장 많은 시간이 걸리는 학생을 구해라.
 * 
 * 여기서 중요하게 볼 점은
 * ** 단방향 **이라는 점이다.
 * 단방향인데, 오고 가는 시간을 구해야 하기 때문에 가는 시간과 오는 시간이 달라질 수 있다는 점을 고려해서 오고 가는 시간을 *2 하면 안된다는 뜻이다.
 * 
 * N명의 학생이 K마을까지 오고 가는 최단시간을 구해야하기 때문에 다익스트라 알고리즘을 시작지점을 1-N까지 해서 N번 돌려야한다.
 * 
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int studentNum;
    public static int roadNum;
    public static int goal;

    public static ArrayList<Node>[] roads;

    public static int INF = 987654321;

    static class Node implements Comparable<Node>{
        int index;
        int cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }        
    }

    public static int[] Dijkstra(int goalNum){
        boolean[] check = new boolean[studentNum+1];
        int[] dist = new int[studentNum+1];

        Arrays.fill(dist, INF);
        dist[goalNum] = 0;

        PriorityQueue<Node> priqueue = new PriorityQueue<>();
        priqueue.offer(new Node(goalNum, 0));

        while(!priqueue.isEmpty()){
            int current = priqueue.poll().index;

            if(check[current]){
                continue;
            }
            check[current] = true;

            for(Node next: roads[current]){
                if(dist[next.index] > dist[current] + next.cost){
                    dist[next.index] = dist[current] + next.cost;
                    priqueue.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        studentNum = Integer.parseInt(st.nextToken());
        roadNum = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        roads = new ArrayList[studentNum+1];

        for (int i = 0; i <= studentNum; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int idx = 0; idx < roadNum; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[start].add(new Node(end, cost));
        }
        int result = 0;
        int[] comeback = Dijkstra(goal);

        // 시작 노드 == 학생 번호
        for(int order = 1; order <= studentNum; order++){
            if(order == goal){
                continue;
            }
            int[] shortLeng = Dijkstra(order);

            if(result < shortLeng[goal] + comeback[order]){
                result = shortLeng[goal] + comeback[order];
            }
        }

        sb.append(result);
        System.out.println(sb);

    }
}