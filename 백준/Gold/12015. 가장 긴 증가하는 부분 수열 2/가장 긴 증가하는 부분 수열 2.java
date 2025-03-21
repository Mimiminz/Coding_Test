import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 12015 가장 긴 증가하는 부분수열2
 * @author JOMINJU
 * 
 * 이분탐색을 이용한 LIS로 풀어보기
 */

 public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static int[] lis;

    public static int binarySearch(int start, int end, int target){
        int mid;

        while(start < end){
            mid = (start + end)/2;

            if(lis[mid] < target){
                start = mid + 1;
            }else{
                end = mid;
            }
        }

        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine().trim());
        int[] numList = new int[number];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int cnt = 0; cnt < number; cnt++){
            numList[cnt] = Integer.parseInt(st.nextToken());
        }

        lis = new int[number+1];
        int len = 0;
        int idx = 0;

		for(int i = 0; i < number; i++) {
			if(numList[i] > lis[len]) {
				len +=1;
				lis[len] = numList[i];
			}else {
				idx = binarySearch(0,len, numList[i]);
				lis[idx] = numList[i];
			}
		}

		System.out.println(len);
        br.close();
    }
}