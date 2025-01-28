import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = br.readLine(); //해당 문자열(N)을 입력 받고
        br.close();

        //reverseOrder() : 큰수 -> 작은수 정렬해주는 메서드를 쓰기 위해 Integer 배열 사용
        Integer[] array = new Integer[N.length()];

        for(int i = 0; i < array.length; i++) {
            //문자열 N의의 각자리의 숫자를 빼서 배열 array에 저장
            // => 숫자이기 때문에 - 0을 해줌 (안해주면 아스키코드값이 들어감)
            array[i] = N.charAt(i) - '0';
        }

        Arrays.sort(array, Collections.reverseOrder()); //큰수 -> 작은수로 정렬

        for(int i = 0; i < array.length; i++) {
            bw.write(array[i]+""); //하나씩 꺼내서 buffer에 쓴다.
        }

        bw.flush();
        bw.close();

    }
}