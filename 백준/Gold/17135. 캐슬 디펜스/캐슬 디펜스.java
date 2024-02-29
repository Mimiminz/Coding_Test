import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/** 
 * BOJ 17135 캐슬 디펜스
 * @author JOMINJU
 * 1. 조합을 통해 궁수가 있을 수 있는 위치를 구한다
 * 2. 각 위치에서 제거할 수 있는 적의 수를 구한다
 * 		2-1. 적의 row 값을 하나씩 내리던가, 궁수의 row값을 하나씩 올려가며 구할 수 있다.
 * 		2-2. 궁수의 row값을 올려가며 적을 제거한 경우에 적의 위치를 false로 바꾼다.
 * 		2-3. 이때, 거리가 D 이하인 적 중 가장 가까운 적, 그 중에서도 가장 왼쪽에 있는 적을 제거한다.
 * 		2-4. 이때 거리는 x값의 차와 y값의 차의 합으로 계산한다.
 * 3. 적을 가장 많이 제거할 수 있는 경우를 구한다.
 * 4. castle 변수를 선언해 1씩 올리고, 해당 변수와 row가 같은 위치에 있는 적은 제외한다
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[] archerList;
	public static boolean[][] enemies;
	public static int rowNum;
	public static int colNum;
	public static int range;
	public static int result;
	
	static final boolean ALIVE = true;
    static final boolean DEAD = false;
	
	static final int SELECT_ARCHER_COUNT = 3;
    static int currentDeadEnemyCount;
    static List<Enemy> copyEnemyList;
    static List<Enemy> originalEnemyList;
    
    public static class Enemy {
        int rowIdx;
        int colIdx;
        boolean alive;

        public Enemy(int rowIdx, int colIdx, boolean alive) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.alive = alive;
        }
    }

    public static void copyArcher() {
        for(Enemy enemy : originalEnemyList) {
            copyEnemyList.add(new Enemy(enemy.rowIdx, enemy.colIdx, ALIVE));
        }
    }

    public static void enemySort(int archerColIdx) {

        int archerRowIdx = rowNum;

        Collections.sort(copyEnemyList, new Comparator<Enemy>() {
            @Override
            public int compare(Enemy enemy1, Enemy enemy2) {

                int enemy1Distance = Math.abs(archerRowIdx - enemy1.rowIdx) + Math.abs(archerColIdx - enemy1.colIdx);
                int enemy2Distance = Math.abs(archerRowIdx - enemy2.rowIdx) + Math.abs(archerColIdx - enemy2.colIdx);

                int distanceDifference= enemy1Distance - enemy2Distance;

                // 거리가 더 짧으면 앞으로
                if(distanceDifference < 0) {
                    return -1;
                }
                // 거리가 더 길면 뒤로
                else if(distanceDifference > 0) {
                    return 1;
                }

                // 거리가 같으면, colIdx 작은 순으로 정렬.
                int colIdxDifference = enemy1.colIdx - enemy2.colIdx;

                if(colIdxDifference < 0) {
                    return -1;
                }
                else if(colIdxDifference > 0) {
                    return 1;
                }

                return 0;
            }
        });
    }

    public static void changeEnemyStatus(int archerColIdx) {

        int archerRowIdx = rowNum;

        for(Enemy enemy: copyEnemyList) {
            if(Math.abs(enemy.rowIdx - archerRowIdx) + Math.abs(enemy.colIdx - archerColIdx) <= range) {
                enemy.alive = DEAD;
                break;
            }
        }
    }

    public static void deadStatusEnemyRemove(boolean addDeadCountFlag) {
        for(int enemyIdx = copyEnemyList.size() - 1; enemyIdx >= 0; enemyIdx--) {
            if(copyEnemyList.get(enemyIdx).alive == DEAD) {
                copyEnemyList.remove(enemyIdx);

                if(addDeadCountFlag) {
                    currentDeadEnemyCount++;
                }
            }
        }
    }

    public static void findDeadEnemy() {

        for(int archerColIdx : archerList) {

        	// 적 제거를 위해 정렬하기
            enemySort(archerColIdx);

            // 제거 가능한 적일 경우 표시
            changeEnemyStatus(archerColIdx);
        }

        // 적 제거
        deadStatusEnemyRemove(true);
    }


    public static void moveEnemy() {
        for(Enemy enemy : copyEnemyList) {
            enemy.rowIdx++;

            if(enemy.rowIdx == rowNum) {
                enemy.alive = DEAD;
            }
        }

        deadStatusEnemyRemove(false);
    }
	
	public static void archerCase(int selectIdx, int elementIdx) {
		if(selectIdx == SELECT_ARCHER_COUNT) {
            currentDeadEnemyCount = 0;

            copyEnemyList = new ArrayList<>();
            copyArcher();

            // 적을 모두 제거할 때까지 반복
            while(!copyEnemyList.isEmpty()) {
            	findDeadEnemy();
            	moveEnemy();
            }

            result = Math.max(result, currentDeadEnemyCount);
			return;
		}
		
		if(elementIdx == colNum)
			return;
		
		archerList[selectIdx] = elementIdx;
		archerCase(selectIdx + 1, elementIdx + 1);
		
		archerList[selectIdx] = 0;
		archerCase(selectIdx, elementIdx + 1);
	}
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		rowNum = Integer.parseInt(st.nextToken());
		colNum = Integer.parseInt(st.nextToken());
		range = Integer.parseInt(st.nextToken());
		archerList = new int[3];
		result = 0;
		originalEnemyList = new ArrayList<>();
        for(int rowIdx = 0; rowIdx < rowNum; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int colIdx = 0; colIdx < colNum; colIdx++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    originalEnemyList.add(new Enemy(rowIdx, colIdx, ALIVE));
                }
            }
        }
        
		archerCase(0, 0);
		
		sb.append(result);
		System.out.println(sb);
		br.close();
		
	}
}
