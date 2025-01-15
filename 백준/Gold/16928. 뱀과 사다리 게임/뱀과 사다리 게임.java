import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 16928 뱀과 사다리 게임
 * @author JOMINJU
 * 
 * 10 * 10 사이즈의 보드판이 있고, 더 높은 숫자로 이동할 수 있게 해주는 사다리와 더 낮은 숫자로 이동하는 뱀이 있다.
 * 이것들을 이용해서 1에서 100까지 주사위를 굴려 도달할 수 있는 최소한의 숫자를 구하는 문제.
 * 
 * 주의점
 * 1. 뱀 칸에 도달하지 않도록 피한다.
 * 2. 최대한 사다리를 이용한다.
 * 
 * 100사이즈의 배열을 만든다.
 * 각 배열에 그 칸까지 이동하는 데 필요한 최소한의 주사위 값을 집어넣는다.
 * 100번째 배열의 값을 출력한다.
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int[] board = new int[101]; // 게임판 (1번 ~ 100번)
    public static boolean[] visited = new boolean[101]; // 방문 여부 체크

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int ladderNum = Integer.parseInt(st.nextToken());
        int snakeNum = Integer.parseInt(st.nextToken());

        // board 초기화: 기본적으로 칸 번호 그대로 저장
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }

        // 사다리 정보 입력
        for (int i = 0; i < ladderNum; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y; // x번 칸에 도달하면 y번 칸으로 이동
        }

        // 뱀 정보 입력
        for (int i = 0; i < snakeNum; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v; // u번 칸에 도달하면 v번 칸으로 이동
        }

        // BFS로 최소 이동 횟수 탐색
        int result = bfs();
        sb.append(result); // 결과를 StringBuilder에 추가
        System.out.println(sb); // 결과 출력
    }

    public static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        int[] moves = new int[101]; // 각 칸까지의 이동 횟수 저장

        queue.add(1); // 시작점
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 100번 칸에 도착하면 최소 횟수 반환
            if (current == 100) {
                return moves[current];
            }

            // 주사위 굴리기 (1 ~ 6)
            for (int dice = 1; dice <= 6; dice++) {
                int next = current + dice;

                // 100번 칸을 넘으면 무시
                if (next > 100) continue;

                // 다음 칸이 사다리/뱀인지 확인
                next = board[next];

                // 방문하지 않은 칸만 이동
                if (!visited[next]) {
                    visited[next] = true;
                    moves[next] = moves[current] + 1; // 이동 횟수 갱신
                    queue.add(next);
                }
            }
        }

        return -1; // 도달 불가한 경우 (문제 조건 상 존재하지 않음)
    }
}