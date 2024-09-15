import java.util.*;

class Main {

	static int N;
	static int[] d;
	static int[] dp;
	
	public static void main(String args[]) {
		
		int max;
		int min;
		
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		d=new int[N+1];
		dp=new int[N+1];
		
		
		for(int i=1;i<=N;i++) {
			d[i]=sc.nextInt();	
			max=Integer.MIN_VALUE;
			min=Integer.MAX_VALUE;
			for(int j=i;j>0;j--) {
				if(max<d[j]) max=d[j];
				if(min>d[j]) min=d[j];
				dp[i]=Math.max(dp[i], max-min+dp[j-1]);
				
			}
		}
			
		
		System.out.println(dp[N]);
		
		
	}
	
}