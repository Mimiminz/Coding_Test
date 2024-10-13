import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int maxPrice;
    public static int[] prices;
    public static int number;

    public static int minPrice;
    public static int minNumber;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        number = Integer.parseInt(br.readLine().trim());
        prices = new int[number];
        minPrice = 51;
        minNumber = 0;
        boolean check = false;

        st = new StringTokenizer(br.readLine().trim());
        maxPrice = Integer.parseInt(br.readLine().trim());
        
        for (int i = 0; i < number; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
            if(minPrice >= prices[i]){
                minNumber = i;
                minPrice = prices[i];
            }

            if(i >= 1 && prices[i] <= maxPrice){
                check = true;
            }

            if(i == number-1 && prices[i] == minPrice){
                minNumber = i;
            }
        }


        if(number == 1 || !check){
            System.out.println(0);
            return;
        }

        int count = 0;
        count = maxPrice / minPrice;
        
        if(count > 1){
            int flag = 51;
            for (int i = 1; i < number; i++) {
                flag = Math.min(flag, prices[i]);
                if(maxPrice - count * minPrice >= prices[i] - minPrice){
                    flag = -1;
                    break;
                }
            }

            if(flag != -1){
                count = (maxPrice-flag) / minPrice + 1;
            }
        }
        
        maxPrice -= count * minPrice;

        for (int i = 0; i < count; i++) {
            boolean write = false;
            if(maxPrice <= 0){
                sb.append(minNumber);
                continue;
            }else{
                for (int j = number-1; j >= i-1; j--) {
                    if(maxPrice >= prices[j] - minPrice){
                        maxPrice -= (prices[j] - minPrice);
                        sb.append(j);
                        write = true;
                        break;
                    }
                }
            }

            if(!write){
                sb.append(minNumber);
            }
        }
        
        System.out.println(sb);
        br.close();
    }
}