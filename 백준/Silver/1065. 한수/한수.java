import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 1065 한수
 * @author JOMINJU
 * 
 * 수가 주어지면 그 수보다 작은 한수의 개수를 구하기
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int base;
    public static int last;
    public static int[] possible = new int[1001];

    public static void main(String[] args) throws IOException {
        base = Integer.parseInt(br.readLine().trim());
        last = 110;
        possible[110] = 99;

        if(base == 1000){
            base = 999;
        }

        if(base < 100){
            System.out.println(base);
        }else if(base < 111){
            System.out.println("99");
        }else{
            int count = possible[last];
            for(int idx = last+1; idx <= base; idx++){
                int one = idx / 100;
                int two = (idx - one * 100) / 10;
                int three = idx - one * 100 - two * 10;

                if((one - two) == (two - three)){
                    count++;
                }

                possible[idx] = count;
            }
            System.out.println(possible[base]);
        }
    }
}