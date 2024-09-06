import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;
    static int number, moveCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        number = Integer.parseInt(st.nextToken());
        moveCount = Integer.parseInt(st.nextToken());
        int answer = 0, index = -1;
        String bitN = Integer.toBinaryString(number);	//N ▶ 2진
        int ones = Integer.bitCount(number);	//2진수 1의 개수
        if(ones > moveCount){	//1의 개수 > K 일 때
            //K번째 '1'을 기준으로 만들어야 하는 형태의 위치 구하기
            for (int i = 0; i < bitN.length(); i++) {
                if (moveCount == 0) {
                    index = i;
                    break;
                }
                if (bitN.charAt(i) == '1')
                moveCount--;
            }
            String temp = bitN.substring(index);	//바꿔야 하는 값
            int t = Integer.parseInt(temp, 2);	//바꿔야 하는 값의 10진수 값

            if (t != 0)
                answer = (int) (Math.pow(2, bitN.length() - index) - t);
        }
        sb.append(answer);
        System.out.println(sb);
        br.close();
    }
}