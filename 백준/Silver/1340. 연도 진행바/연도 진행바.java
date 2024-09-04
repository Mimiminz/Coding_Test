import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    // 윤년 때문에 각 월이 며칠인지 구하는 메서드
    public static int[] getYear(int year){
        int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(year%4==0 && year%100 != 0 || year%400==0){
            month[1] = 29;
        }
        return month;
    }
    // 월이 영어로 되어 있어서 숫자로 변환하는 메서드
    public static int getMonth(String month){
        int result = 0;
        if(month.equals("January")) result = 1;
        else if(month.equals("February")) result = 2;
        else if(month.equals("March")) result = 3;
        else if(month.equals("April")) result = 4;
        else if(month.equals("May")) result = 5;
        else if(month.equals("June")) result = 6;
        else if(month.equals("July")) result = 7;
        else if(month.equals("August")) result = 8;
        else if(month.equals("September")) result = 9;
        else if(month.equals("October")) result = 10;
        else if(month.equals("November")) result = 11;
        else if(month.equals("December")) result = 12;

        return result;
    }
    // 진행된 날짜 구하기
    public static int getDays(int[] months,  int month, int day){
        int Days = 0;
        for(int i=0; i<month-1; i++){
            Days += months[i];
        }
        Days+=day-1;
        return Days;
    }
    // 날짜 시간으로 변환하는 메서드
    public static int getTime(int day, int hour, int minute){
        int time = 0;
        time = (1440 * day);
        time += (60 * hour);
        time += minute;
        return time;
    }
    // 해당하는 년도 시간으로 변환하는 메서드
    public static int getDTime(int[] months){
        int dDay = 0;
        int time = 0;
        for(int i=0; i<months.length; i++){
            dDay += months[i];
        }
        time = (dDay * 1440);
        return time;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputAnswer = br.readLine().split(" ");
        int month = getMonth(inputAnswer[0]);
        int[] months = getYear(Integer.parseInt(inputAnswer[2]));
        int day = Integer.parseInt(inputAnswer[1].substring(0,2));
        int tDay = getDays(months, month, day);
        int hour = Integer.parseInt(inputAnswer[3].substring(0,2));
        int minute = Integer.parseInt(inputAnswer[3].substring(3,5));
        int tTime = getTime(tDay, hour, minute);
        int dTime = getDTime(months);
        Double result = Double.parseDouble(String.valueOf(tTime)) / Double.parseDouble(String.valueOf(dTime)) * 100;
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();

    }
}