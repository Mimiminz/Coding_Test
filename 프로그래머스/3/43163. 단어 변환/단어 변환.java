import java.io.*;
import java.util.*;

class Solution {
    
    public int wordLength;
    
    public boolean StringCheck(String str1, String str2){
        int check = 0;
        for(int idx = 0; idx < wordLength; idx++){
            if(str1.charAt(idx) != str2.charAt(idx)){
                check++;
            }
        }
        
        if(check == 1){
            return true;
        }
        
        return false;
        
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = -1;
        wordLength = begin.length();
        Queue<String> queue = new LinkedList<>();
        int visited[] = new int[words.length+1];
        queue.offer(begin);
        String current = "";
        
        while(!queue.isEmpty()){
            current = queue.poll();
            
            int index = current.equals(begin) ? 0 : Arrays.asList(words).indexOf(current);
            if(current.equals(target)){
                answer = visited[index];
                break;
            }
            
            answer++;
            
            for(int idx = 0; idx < words.length; idx++){
                if(visited[idx] == 0 && StringCheck(current, words[idx])){
                    visited[idx] = visited[index]+1;
                    queue.offer(words[idx]);
                }   
            }
        }
        
        if(!current.equals(target)){
            answer = 0;
        }
        
        return answer;
    }
}