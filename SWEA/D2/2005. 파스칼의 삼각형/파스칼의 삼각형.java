import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution{
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static int[] pascal;
	public static int[] beforePascal;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			int size = Integer.parseInt(br.readLine().trim());
			beforePascal = new int[size];
			pascal = new int[size];
			pascal[0] = 1;
			sb.append("#").append(tc).append("\n").append(1).append("\n");
			for(int col = 1; col < size; col++) {
				for(int row = 0; row <= col; row++) {
					if(row == 0 || row == col) {
						pascal[row] = 1;
						sb.append(1).append(" ");
					}else {
						pascal[row] = beforePascal[row-1] + beforePascal[row];
						sb.append(pascal[row]).append(" ");
					}
				}
				sb.append("\n");
				for(int row = 0; row <= col; row++) {
					beforePascal[row] = pascal[row];
				}
			}
		}
		System.out.println(sb);
		br.close();
	}
}