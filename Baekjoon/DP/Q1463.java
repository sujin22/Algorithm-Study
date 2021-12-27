package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1463 {
    public static Integer[] dpArr; //int의 경우 초기값이 0이므로, 초기값이 null이어야해서 Integer 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        dpArr = new Integer[x+1];
        dpArr[1] = 0;   //1이 되기 위한 횟수 0번

        System.out.println(makeOne(x));
    }

    public static int makeOne(int n){
        if(n==1){
            return dpArr[1];
        }

        int min = n; //제일 큰 n으로 초기화

        //아직 배열에 저장되지 않은 수면 각각의 경우에서 최솟값에 +1해준다.
        if(dpArr[n] == null){
            if(n%3==0){
                min = Integer.min(min, makeOne(n/3));
            }
            if(n%2 == 0){
                min = Integer.min(min, makeOne(n/2));
            }
            //n-1
            min = Integer.min(min, makeOne(n-1));

            dpArr[n] = min+1;
        }
        return dpArr[n];
    }
}
