import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 15989 1,2,3 더하기 4
 * @author JOMINJU
 * 
 * 정수 n이 주어졌을때 1,2,3의 합으로 나타낼 수 있는 가짓수를 구하는 문제
 * 
 * 생각방향 1번. 1,2,3의 각 개수를 HashSet을 이용해서 중복 제거를 하고, 나올 수 있는 경우의 수를 구하기
 * 생걱벙형 2번. 해설을 좀 참고했다. 나중에 다시 풀어봐야 할 듯 하다.
 * 풀이에서 좀 참고해야 할 점은 정렬을 하는 것과 특정 조건을 부합하는 걸로 나눴다는 점이다.
 * 
 * 즉, 정렬 -> 중복 제거에 쓰였다. 오름차순으로 정렬해서 오름차순에 해당되지 않는 것은 체크하지 않는 것이다. 이것을 이용해서 중복을 체크한다.
 * 특정 조건 -> 이건 끝이 +1로 끝나는지, +2인지, +3인지를 구별해서 개수를 체크하는 것이다.
 * 오름차순으로 정렬되기 때문에 +1로 끝나는 것을 구하기 위해서는 이전 숫자의 +1로 끝나는 것들을 참고해야한다는 것이다.
 * 물론 +2로 끝나는 것을 구하기 위해선 2번째 전의 숫자에서 +1과 +2로 끝나는 숫자에 +2를 하는 것이다.
 * 
 */

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int testCase;
    public static int lastNum;
    public static int[][] results;

    public static void main(String[] args) throws Exception {
        testCase = Integer.parseInt(br.readLine().trim());
        lastNum = 3;
        results = new int[10001][4];

        results[1][1] = 1;
        results[1][2] = 0;
        results[1][3] = 0;

        results[2][1] = 1;
        results[2][2] = 1;
        results[2][3] = 0;
        
        results[3][1] = 1;
        results[3][2] = 1;
        results[3][3] = 1;
        

        for(int T = 0; T < testCase; T++){
            int n = Integer.parseInt(br.readLine().trim());

            if(n > lastNum){
                for(int start = lastNum+1; start <= n; start++){
                    results[start][1] = results[start-1][1];

                    results[start][2] = results[start-2][1] + results[start-2][2];

                    results[start][3] = results[start-3][1] + results[start-3][2] + results[start-3][3];
                }

                lastNum = n;
            }
            sb.append(results[n][1] + results[n][2] + results[n][3]).append("\n");
        }

        System.out.println(sb);
    }
}