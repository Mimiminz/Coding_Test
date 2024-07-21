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

        int goal = Integer.parseInt(br.readLine().trim());
        int stick = 64;
        int temp = 0;
        int count = 0;
        int result = goal;

        while(true){
            if(goal == 64){
                count++;
                break;
            }

            stick /= 2;
            if(stick <= goal){{
                temp += stick;
                count++;
                if(result == temp){
                    break;
                }
                goal -= stick;
            }}
        }

        sb.append(count);
        System.out.println(sb);
    }
}