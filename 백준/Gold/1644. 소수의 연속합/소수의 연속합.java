import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static boolean[] prime;

    public static void make_prime(int N){
        prime = new boolean[N + 1];	

		if(N < 2) {
			return;
		}
        
		prime[0] = prime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
        
			if(prime[i] == true) {
				continue;
			}
        
			for(int j = i * i; j < prime.length; j = j+i) {
				prime[j] = true;
			}
		}
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int goal = Integer.parseInt(br.readLine().trim());
        ArrayList<Integer> arr = new ArrayList<>();

        make_prime(goal);

        for(int i = 2; i < prime.length; i++) {
			if(prime[i] == false) {	
				arr.add(i);
			}
		}

        int start = 0;
        int end = 0;
        if(arr.size() == 0){
            System.out.println(0);
            return;
        }
        int sum = arr.get(0);
        int result = 0;
        while(start <= end){
            if(sum == goal){
                result++;
                end++;
                if(end >= arr.size()){
                    break;
                }
                sum += arr.get(end);
            }else if(sum < goal){
                end++;
                if(end >= arr.size()){
                    break;
                }
                sum += arr.get(end);
            }else{
                sum -= arr.get(start);
                start++;
            }
        }

        sb.append(result);
        
        System.out.println(sb);
        br.close();
    }
}