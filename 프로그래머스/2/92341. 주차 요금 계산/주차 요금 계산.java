import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        HashMap<String, Integer> carInfo = new HashMap<>();
        HashMap<String, Integer> wholeTime = new HashMap<>();
        ArrayList<String> check = new ArrayList<>();
        StringTokenizer st;
        
        for(int idx = 0; idx < records.length; idx++){
            st = new StringTokenizer(records[idx]);
            String Time = st.nextToken();
            int intTime = Integer.parseInt(Time.substring(0,2)) * 60 + Integer.parseInt(Time.substring(3,5));
            String carNumber = st.nextToken();
            String state = st.nextToken();
            
            if(state.equals("IN")){
                carInfo.put(carNumber, intTime);
                check.add(carNumber);
            }else{
                int inTime = carInfo.get(carNumber);
                int totalTime = intTime - inTime;
                if(wholeTime.containsKey(carNumber)){
                    wholeTime.put(carNumber, wholeTime.get(carNumber) + totalTime);
                }else{
                    wholeTime.put(carNumber, totalTime);
                }
                check.remove(carNumber);
            }
        }
        for(int non = 0; non < check.size(); non++){
            int inTime = carInfo.get(check.get(non));
            int totalTime = 1439 - inTime;
            
            if(wholeTime.containsKey(check.get(non))){
                wholeTime.put(check.get(non), wholeTime.get(check.get(non)) + totalTime);
            }else{
                wholeTime.put(check.get(non), totalTime);
            }
        }

        List<String> sortedKeys = new ArrayList<>(carInfo.keySet());
        Collections.sort(sortedKeys);
        answer = new int[sortedKeys.size()];

        for (int i = 0; i < sortedKeys.size(); i++) {
            int times = wholeTime.get(sortedKeys.get(i));
            int carcul = (times - basicTime) / unitTime;
            if((times - basicTime) % unitTime != 0){
                carcul++;
            }
            answer[i] = times <= basicTime ? basicFee : basicFee + carcul * unitFee;
        }
    
        
        return answer;
    }
}