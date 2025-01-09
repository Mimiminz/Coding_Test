import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * BOJ 1931 회의실 배정
 * @author JOMINJU
 *
 * 각 회의에 대해 시작시간과 끝이 정해져 있고, 하나의 회의실에서 할 수 있는 회의의 최대 개수를 구해라
 * 
 * 이차원 배열의 두번째 인덱스를 중심으로 배열 정렬
 * 이후 끝나는 시간이 가장 이른 회의부터 진행
 * 진행할 수 있는 회의 중 끝나는 시간이 가장 이른 회의부터 진행을 반복하여 회의 개수 구함
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int meetingCount;
    public static int[][] meetingTimes;
    public static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        meetingCount = Integer.parseInt(br.readLine().trim());
        meetingTimes = new int[meetingCount][2];
        check = new boolean[meetingCount];

        for(int idx = 0; idx < meetingCount; idx++){
            st = new StringTokenizer(br.readLine().trim());
            meetingTimes[idx][0] = Integer.parseInt(st.nextToken());
            meetingTimes[idx][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meetingTimes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int startTime = meetingTimes[0][1];
        int result = 1;
        check[0] = true;

        for(int cnt = 1; cnt < meetingCount; cnt++){
            if(meetingTimes[cnt][0] >= startTime && !check[cnt]){
                check[cnt] = true;
                startTime = meetingTimes[cnt][1];
                result++;
            }
        }

        sb.append(result);
        System.out.println(sb);
	}
}