import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ 22866 탑 보기
 * @author JOMINJU
 * 
 * 각 건물의 높이보다 큰 건물만 볼 수 있음.
 * 이때, 각 건물에서 볼 수 있는 건물의 개수와, 볼 수 있는 건물 중 가장 거리가 가깝고 작은 건물의 번호를 출력하기.
 * 
 * #주의점
 * 1. 앞에 높이가 더 큰 건물이 있으면 다음 건물은 가려져서 보이지 않음
 * 2. 가장 가깝고, 숫자가 낮은 건물 번호를 출력해야함
 */

 public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int size;
    public static int[] height;
    public static int[][] near;

    public static void main(String[] args) throws Exception {
        size = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        height = new int[size+1];
        near = new int[size+1][2];
        
        for(int idx = 1; idx <= size; idx++){
            height[idx] = Integer.parseInt(st.nextToken());
        }

        //answer 0번 => 보이는 건물 갯수 | 1번 => 가장 가까운 건물 번호
        List<int[]> answer = new ArrayList<>();
        for (int idx = 0; idx <= size; idx++) {
            answer.add(new int[] {0, 0});
        }

        Stack<int[]> stacks = new Stack<>();

        // 1번 미리 넣기
        stacks.add(new int[]{1, height[1]});
        for(int idx = 2; idx <= size; idx++){
            int[] current = answer.get(idx);

            while (!stacks.isEmpty()){
                int[] peek = stacks.peek();
                if (peek[1] <= height[idx]){
                    stacks.pop();
                }else
                    break;
            }

            if (!stacks.isEmpty()){
                int[] peek = stacks.peek();
                current[1] = peek[0];
            }
            current[0] += stacks.size();
            stacks.add(new int[]{idx, height[idx]});
        }

        stacks.clear();

        // 제일 마지막 건물 미리 넣기
        stacks.add(new int[]{size, height[size]});
        for(int idx = size; idx >= 1; idx--){
            int[] current = answer.get(idx);

            while (!stacks.isEmpty()){
                int[] peek = stacks.peek();
                if (peek[1] <= height[idx]){
                    stacks.pop();
                }else
                    break;
            }

            if (!stacks.isEmpty()){
                int[] peek = stacks.peek();
                if (current[0] == 0)
                    current[1] = peek[0];
                else{
                    int prev = Math.abs(idx - current[1]);
                    int next =  Math.abs(idx - peek[0]);
                    if (next < prev)
                        current[1] = peek[0];
                }
            }
            current[0] += stacks.size();
            stacks.add(new int[]{idx, height[idx]});
        }

        for (int idx = 1; idx <= size; idx++) {
            int[] list = answer.get(idx);
            if (list[0] == 0){
                sb.append(0).append("\n");
            }else{
                sb.append(list[0]).append(" ").append(list[1]).append("\n");
            }
        }

        System.out.println(sb);

    }
}