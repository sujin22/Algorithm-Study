package boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11060_점프점프 {
    /*[실버2]Q11060 - 점프 점프*/
    static int n;
    static int[] arr;
    static boolean[] visited;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i< n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        goEnd(0);
        if(dp[n-1] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[n-1]);
    }

    static void goEnd(int x){
        for(int i = 1; i<=arr[x]; i++){
            if(x+i >n-1)   continue;
            if(dp[x+i] > dp[x]+1){
                dp[x+i] = dp[x]+1;
                goEnd(x+i);
            }
        }
    }
}
