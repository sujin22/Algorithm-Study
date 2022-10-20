package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2659_십자카드문제 {
    final static int LENGTH = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[LENGTH];
        for(int i = 0; i<LENGTH; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int clock = getClock(arr);
        System.out.println(getOrder(clock));
    }
    private static int getClock(int[] arr){
        int min = makeInt(arr);
        for(int i =1; i<LENGTH; i++){
            int[] now = new int[LENGTH];
            for(int ind=0; ind<LENGTH; ind++){
                int arrIndex = (i+ind) % LENGTH;
                now[ind] = arr[arrIndex];
            }
            min = Integer.min(min, makeInt(now));
        }
        return min;
    }
    private static int makeInt(int[] arr){
        return arr[0]*1000 + arr[1]*100 + arr[2]*10 + arr[3];
    }
    //숫자를 배열로 만들어주는 함수
    private static int[] makeArr(int num){
        String str = num+"";
        int length = str.length();
        int[] arr = new int[length];
        int i = length-1;
        while(num!=0){
            arr[i--] = num%10;
            num/=10;
        }
        return arr;
    }
    private static boolean isClock(int[] arr){
        if(makeInt(arr) == getClock(arr))   return true;
        else    return false;
    }
    private static boolean hasZero(int num){
        int[] arr = makeArr(num);
        for(int i = 0; i<LENGTH; i++){
            if(arr[i] == 0){
                return true;
            }
        }
        return false;
    }

    //clock이 몇 번째 시계수인지?
    private static int getOrder(int clock){
        int count = 0;
        for(int i = 1111; i<=clock;i++){
            if(hasZero(i)||!isClock(makeArr(i)))  continue;
            count++;
        }
        return count;
    }
}
