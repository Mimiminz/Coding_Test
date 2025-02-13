class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1;
        int end = 100000;
        int mid = 0;
        while(start <= end){
            mid = (start + end)/2;
            long current = times[0];
            for(int cnt = 1; cnt < diffs.length; cnt++){
                if(diffs[cnt] <= mid){
                    current += times[cnt];
                }else{
                    current += (diffs[cnt] - mid) * (times[cnt] + times[cnt-1]) + times[cnt];
                }
            }
            
            if(current > limit){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        
        return end+1;
    }
}