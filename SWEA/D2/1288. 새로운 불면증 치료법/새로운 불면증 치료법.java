import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int testCase = Integer.parseInt(br.readLine());
        int total = (1<<10)-1;
 
        for(int tc = 1; tc <= testCase; tc++){
            int N = Integer.parseInt(br.readLine());
            int count = 0;
            int visited = 0;
            while(true){
                String strNum = String.valueOf((N*(++count)));
                for(char c: strNum.toCharArray()){
                    int num = c - '0';
                    visited |= (1<<num);
                }
                if(visited == total)
                    break;
            }
            System.out.printf("#%d %d\n", tc, count*N);
        }
    }
}