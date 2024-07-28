import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class Main {
    public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;

    public static int count;
    public static int location;
    public static int[][] time;
    public static int result = Integer.MAX_VALUE;

    public static boolean[] visited;

    public static void findDist(int level, int start, int sum){
        if(level == count-1){
            result = Math.min(result, sum);
            return;
        }

        for (int cnt = 0; cnt < count; cnt++) {
            if(!visited[cnt]){
                visited[cnt] = true;
                findDist(level+1, cnt, sum+time[start][cnt]);
                visited[cnt] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        count = Integer.parseInt(st.nextToken());
        location = Integer.parseInt(st.nextToken());

        time = new int[count][count];
        visited = new boolean[count];

        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < count; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    if(j == k){
                        continue;
                    }
                    time[j][k] = Math.min(time[j][k], time[j][i]+time[i][k]);
                }
            }
        }

        visited[location] = true;
        findDist(0, location, 0);
       

        sb.append(result);
        System.out.println(sb);
    }
}