import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class Main {
    public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
    public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
        int count = st.countTokens();
        int result = 0;
        int[] block = new int[count];
        for(int i = 0; i < count; i++){
            block[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i < count - 1; i++) { //인덱스 별 모이는 빗물. 첫, 마지막 제외
            int left = 0;
            int right = 0;
 
            for(int j = 0; j < i; j++) {
                left = Math.max(block[j], left);
            }
 
            for(int j = i + 1; j < count; j++) {
                right = Math.max(block[j], right);
            }
 
            if(block[i] < left && block[i] < right) result += Math.min(left, right) - block[i];
        }
        sb.append(result);
        System.out.println(sb);
    }
}