import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * SWEA 1873 상호의 배틀필드
 * @author JOMINJU
 *
 *	1. 게임 맵의 상태를 입력받는다.
 *	2/ 
 *	2. 명령어를 입력받으며 하나씩 실행한다.
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	// 상 하 좌 우
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static char[][] gameMap;
	
	public static int height;
	public static int width;
	public static int currentX;
	public static int currentY;
	public static int direction;
	
	public static void moveTank(char cmd) {
		switch(cmd) {
			case 'U':
	            if( currentX - 1 >= 0 && gameMap[currentX-1][currentY] == '.') {
	            	gameMap[currentX][currentY] = '.';
	            	currentX--;
	            }
	            gameMap[currentX][currentY] = '^';
	            direction = 0;
	            break;
			case 'D':
	        	if( currentX + 1 < height && gameMap[currentX+1][currentY] == '.') {
	            	gameMap[currentX][currentY] = '.';
	            	currentX++;
	            }
	            gameMap[currentX][currentY] = 'v';
	            direction = 1;
	            break;
			case 'L':
	        	if( currentY - 1 >= 0 && gameMap[currentX][currentY-1] == '.') {
	            	gameMap[currentX][currentY] = '.';
	            	currentY--;
	            }
	            gameMap[currentX][currentY] = '<';
	            direction = 2;
	            break;
			case 'R':
				if( currentY + 1 < width && gameMap[currentX][currentY+1] == '.') {
	            	gameMap[currentX][currentY] = '.';
	            	currentY++;
	            }
	            gameMap[currentX][currentY] = '>';
	            direction = 3;   
	            break;
			case 'S':
				int shellX = currentX;
				int shellY = currentY;
	            while(true) {
	            	shellX += dx[direction];
	            	shellY += dy[direction];
	                if( shellX < 0 || shellX >= height || shellY < 0 || shellY >= width
	                		|| gameMap[shellX][shellY] == '#')
	                    break;
	                if(gameMap[shellX][shellY] == '*') {
	                	gameMap[shellX][shellY] = '.';
	                    break;
	                }
	            }
	            break;
		}
	}
		
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			gameMap = new char[height][width];
			for(int row = 0; row < height; row++) {
				String rowStr = br.readLine().trim();
				for(int col = 0; col < width; col++) {
					gameMap[row][col] = rowStr.charAt(col);
					switch(gameMap[row][col]) {
						case '^':
							currentX = row;
							currentY = col;
							direction = 0;
							break;
						case 'v':
							currentX = row;
							currentY = col;
							direction = 1;
							break;
						case '<':
							currentX = row;
							currentY = col;
							direction = 2;
							break;
						case '>':
							currentX = row;
							currentY = col;
							direction = 3;
							break;
						default:
							continue;
					}
				}
			}
			int cmdNum = Integer.parseInt(br.readLine().trim());
			String cmd = br.readLine().trim();
			for(int idx = 0; idx < cmdNum; idx++) {
				moveTank(cmd.charAt(idx));
			}
			
			sb.append("#").append(tc).append(" ");
			for(int row = 0; row < height; row++) {
				for(int col = 0; col < width; col++) {
					sb.append(gameMap[row][col]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
}