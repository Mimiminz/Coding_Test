import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    
    
    public static void main(String[] args) throws IOException{
        String str = br.readLine().trim();

        int total = 0; // 입력 받은 문자열에서 a의 개수
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') total++;
        }

        int aMax = 0; // 부분 문자열에서 a의 개수 중 최댓값
        for (int i = 0; i < str.length(); i++) {
            int aCnt = 0; // 부분 문자열에서 a의 개수 카운트

                for (int j = 0; j < total; j++) {
                // 입력 문자열의 범위를 벗어나면 다시 0부터 시작해야함
                int index = (i + j < str.length() ? i + j : i + j - str.length()); 

                if (str.charAt(index) == 'a') aCnt++;

                if (aCnt > aMax) aMax = aCnt; // 최댓값과 비교
            }
        }
        sb.append(total-aMax);
        System.out.println(sb);
    }
}