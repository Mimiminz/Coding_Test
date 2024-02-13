import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int needKg = Integer.parseInt(br.readLine().trim());
		int remain = needKg;
		int flag = 0;
		int result = Integer.MAX_VALUE;
		
		for(int idx = 1; idx <= needKg/5; idx++) {
			remain = needKg - (5*idx);
			if(remain % 3 == 0) {
				result = Math.min(result, idx + remain / 3);
				flag = 1;
			}
		}
		if(needKg % 3 == 0)
			result = Math.min(result, needKg / 3);
		else if(flag == 0)
			result = -1;
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
}