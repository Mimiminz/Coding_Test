import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * BOJ 2263 트리의 순회
 * @author JOMINJU
 * 
 * 전위, 후위, 중위
 * 중위는 (전, 중, 후)의 순서
 * 후위는 (전, 후, 중)의 순서.
 * 후뤼를 통해 루트 노드를 알 수 있고, 루트 노드와 중위순회를 통해 왼족 트리와 오른쪽 트리를 알 수 있음
 * 이를 기반으로 순서 찾기
 * 
 */

 public class Main {
    static int nodeNum;
    static int[] inOrder, postOrder;
    static Map<Integer, Integer> inOrderIndexMap;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeNum = Integer.parseInt(br.readLine().trim());
        inOrder = new int[nodeNum];
        postOrder = new int[nodeNum];
        inOrderIndexMap = new HashMap<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine().trim());
        StringTokenizer st2 = new StringTokenizer(br.readLine().trim());

        for (int i = 0; i < nodeNum; i++) {
            inOrder[i] = Integer.parseInt(st1.nextToken());
            inOrderIndexMap.put(inOrder[i], i); // 값 -> 인덱스 저장
        }

        for (int i = 0; i < nodeNum; i++) {
            postOrder[i] = Integer.parseInt(st2.nextToken());
        }

        findPreOrder(0, nodeNum - 1, 0, nodeNum - 1);

        System.out.println(sb);
    }

    static void findPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int root = postOrder[postEnd];
        sb.append(root).append(" ");

        int rootIndex = inOrderIndexMap.get(root); // 빠르게 루트 위치 찾기
        int leftSize = rootIndex - inStart; // 왼쪽 서브트리 크기

        // 왼쪽 서브트리 재귀 호출
        findPreOrder(inStart, rootIndex - 1, postStart, postStart + leftSize - 1);

        // 오른쪽 서브트리 재귀 호출
        findPreOrder(rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1);
    }
}