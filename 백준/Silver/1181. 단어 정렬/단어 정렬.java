import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * BOJ 1181 단어 정렬
 * @author JOMINJU
 * 
 * 주어진 알파벳을 길이, 사전순으로 정렬 후 출력하기
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int strNum;
    public static HashSet<String> hashStr;

    public static void main(String[] args) throws IOException {
        strNum = Integer.parseInt(br.readLine().trim());
        hashStr = new HashSet<>();

        for(int idx = 0; idx < strNum; idx++){
            hashStr.add(br.readLine().trim());
        }

        ArrayList<String> str = new ArrayList<>(hashStr);

        Collections.sort(str, new StrComparator());

        for(String order: str){
            sb.append(order).append("\n");
        }
        System.out.println(sb);
    }

    static class StrComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if(o1.length() == o2.length()){
                return o1.compareTo(o2);
            }else{
                return o1.length() > o2.length() ? 1 : -1;
            }
        }
    }
}
