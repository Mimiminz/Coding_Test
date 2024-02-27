import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int number = Integer.parseInt(br.readLine().trim());
		int[] numCount = new int[number+1];
		
		for (int idx = 2; idx <= number; idx++) {
			numCount[idx] = numCount[idx-1]+1;
			if(idx % 2 == 0)
				numCount[idx] = Math.min(numCount[idx], numCount[idx/2]+1);
			if(idx % 3 == 0)
				numCount[idx] = Math.min(numCount[idx], numCount[idx/3]+1);
		}
		sb.append(numCount[number]);
		System.out.println(sb);
	}
}