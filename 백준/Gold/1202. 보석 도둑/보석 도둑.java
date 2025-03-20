import java.io.*;
import java.util.*;

public class Main {
    static class Jewel implements Comparable<Jewel> {
        int weight, value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight; // 무게 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        Jewel[] jewels = new Jewel[N]; // 보석 배열
        int[] bags = new int[K]; // 가방 배열

        // 보석 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int M = Integer.parseInt(st.nextToken()); // 무게
            int V = Integer.parseInt(st.nextToken()); // 가격
            jewels[i] = new Jewel(M, V);
        }

        // 가방 입력
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine().trim());
        }

        // **Step 1: 보석 & 가방 정렬**
        Arrays.sort(jewels); // 보석은 무게 기준 오름차순 정렬
        Arrays.sort(bags); // 가방도 무게 기준 오름차순 정렬

        // **Step 2: 가장 비싼 보석을 우선순위 큐(최대 힙)로 관리**
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long result = 0;
        int jewelIdx = 0;

        // **Step 3: 가방 순서대로 보석 선택**
        for (int bag : bags) {
            // 현재 가방에 담을 수 있는 보석을 우선순위 큐에 추가
            while (jewelIdx < N && jewels[jewelIdx].weight <= bag) {
                pq.offer(jewels[jewelIdx].value);
                jewelIdx++;
            }

            // **현재 가방에 넣을 수 있는 가장 비싼 보석 선택**
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);
    }
}
