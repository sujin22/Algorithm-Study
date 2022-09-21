package boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4811_알약 {
    /*[골드5]Q4811 - 알약*/
    static long[][] dp = new long[31][61];

    public static void main(String[] args) throws IOException {
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[0][0] = 0;
        dp[0][1] = 1;
        dp[1][0] = 1;


        while(true){
            N = Integer.parseInt(br.readLine());
            if(N==0)    break;

            System.out.println(getValue(N,0));
        }
    }
    static long getValue(int whole, int half){
        if(dp[whole][half]!=0) return dp[whole][half];

        //반 개가 없을 경우
        if(half == 0)
            return dp[whole][half] = getValue(whole-1, half+1);
            //쪼갤 알약이 없을 경우
        else if(whole ==0)
            return dp[whole][half] = getValue(whole, half-1);
            //반 개, 쪼갤 알약이 둘 다 있을 경우
        else
            return dp[whole][half] = getValue(whole-1, half+1)
                    + getValue(whole, half-1);
    }
}
