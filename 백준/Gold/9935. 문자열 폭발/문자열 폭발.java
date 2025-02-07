import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 9935 문자열 폭발
 * @author JOMINJU
 * 
 * 문자열에 폭발 문잦열이 포함되어 있으면 문자열에 있는 모든 폭발 문자열이 터지고, 남는 문자열을 모아 새로운 문자열을 만든다.
 * 새로운 문자열에도 폭발 문자열이 있을 수 있다.
 * 
 * 남는 문자열을 출력하고, 문자가 남아있지 않다면 "FRULA"를 출력한다.
 * 
 * 문자열에 폭발 문자열이 있는지 확인하고 하나라도 있다면 폭발 문자열 이전 이후의 문자열을 모아 새롭게 만든다.
 * while문을 통해 문자열이 없을 때까지 반복한다.
 * 문자열이 없다면 출력한다.
 * 
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static String str;
    public static String boom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().trim();
        String boom = br.readLine().trim();
        br.close();
        
        StringBuilder sb = new StringBuilder();
        int boomLen = boom.length();

        for (char c : str.toCharArray()) {
            sb.append(c);

            if (sb.length() >= boomLen && sb.substring(sb.length() - boomLen).equals(boom)) {
                sb.setLength(sb.length() - boomLen);
            }
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}