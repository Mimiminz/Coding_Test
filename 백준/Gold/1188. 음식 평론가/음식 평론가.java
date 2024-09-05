import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1188 음식 평론가
 * 
 * 1. 소세지의 개수가 평론가의 수보다 많을 때
 * 1.1 소세지/평론가 했을 때 나머지가 0이면 0 출력
 * 1.2 나머지가 0이 아닐 경우, 일단 몫 만큼 감소, 이후 카운트 늘리고 남은 거 놔두기
 * 1.3 반복
 * 2. 소새지의 개수가 평론가의 수보다 적을 때
 * 2.1 필요한 만큼 자르기 (커팅 횟수 +1)
 * 2.2 남은 부분이 분배해야 하는 양보다 크면 재사용
 * 2.3 작지 않을 경우
 */

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();

        int sausageCount = Integer.parseInt(st.nextToken());
        int critic = Integer.parseInt(st.nextToken());
        int cutCount = 0;
        // 분모는 무조건 평론가의 수, 분자를 나타내는 값
        int remain = 0;

        if (sausageCount > critic) {
            sausageCount -= sausageCount / critic * critic;
        }

        if (sausageCount != critic && sausageCount != 0) {
            for (int number = 0; number < critic; number++) {
                if (remain == 0) {
                    cutCount++;
                    remain = critic - sausageCount;
                } else if (remain > sausageCount) {
                    cutCount++;
                    remain -= sausageCount;
                } else if (remain == sausageCount) {
                    remain = 0;
                } else {
                    cutCount++;
                    remain = critic - sausageCount + remain;
                }
            }
        }

        sb.append(cutCount);
        System.out.println(sb);
        br.close();

    }
}
