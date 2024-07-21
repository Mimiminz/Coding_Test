import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class Main {
    public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
    public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

        int count = Integer.parseInt(br.readLine().trim());
        Integer[] AList = new Integer[count];
        Integer[] BList = new Integer[count];
        
        st = new StringTokenizer(br.readLine().trim());
        for(int a = 0; a < count; a++){
            AList[a] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(AList);

        st = new StringTokenizer(br.readLine().trim( ));
        for(int b = 0; b < count; b++){
            BList[b] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(BList, Collections.reverseOrder());

        int result = 0;
        for(int cnt = 0; cnt < count; cnt++){
            result += AList[cnt] * BList[cnt];
        }

        sb.append(result);
        System.out.println(sb);
    }
}