import java.io.*;
import java.util.*;

public class Main{
    static ArrayList<Integer> plus = new ArrayList<>();	//양수 저장 리스트
    static ArrayList<Integer> minus = new ArrayList<>();	//음수 저장 리스트
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        int max = -1;
        boolean check = false;
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(st.nextToken());
            if(Math.abs(n) > max){
                max = Math.abs(n);
                check = n > 0;
            }
            if(n>0)
                plus.add(n);
            else
                minus.add(n);
        }
        Collections.sort(plus);	
        Collections.sort(minus, Collections.reverseOrder());
        int answer = 0;

        answer += minusSearch(!check);	
        answer += plusSearch(check);	

        bw.write(answer + "");	
        bw.flush();		
        bw.close();
        br.close();
    }
    static int plusSearch(boolean check){
        int c = plus.size()%M;
        int result = 0;
        if(c != 0){
            if(plus.size()/M > 0)
                result = plus.get(c-1)*2;
            else
                result = plus.get(c-1);
        }
        if(plus.size()/M > 0){
            int count = 0;
            for(int i=c;i<plus.size();i++){
                count++;
                if(count == M){
                    count = 0;
                    if(i == plus.size()-1)
                        result += plus.get(i);
                    else
                        result += plus.get(i)*2;
                }
            }
        }
        if(!check && !plus.isEmpty())
            result += plus.get(plus.size()-1);
        return result;
    }
    static int minusSearch(boolean check){
        int c = minus.size()%M;
        int result = 0;
        if(c != 0){
            if(minus.size()/M > 0)
                result = minus.get(c-1)*2;
            else
                result = minus.get(c-1);
        }
        if(minus.size() / M > 0){
            int count = 0;
            for(int i=c;i<minus.size();i++){
                count++;
                if(count == M){
                    count = 0;
                    if(i == minus.size()-1)
                        result += minus.get(i);
                    else
                        result += minus.get(i) * 2;
                }
            }
        }
        if(!check && !minus.isEmpty())
            result += minus.get(minus.size()-1);
        return Math.abs(result);
    }
}